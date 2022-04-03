package hos.util.cache;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import hos.core.AppCompat;
import hos.util.func.Function1;
import hos.util.singleton.ISingletonWrapper;
import hos.util.singleton.SingletonWeakManager;

/**
 * <p>Title: CacheDaoImpl </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2022/4/2 17:49
 */
public class CacheDaoSharedPreferences implements CacheDao, ISingletonWrapper {

    private SharedPreferences sp() {
        return AppCompat.getApp().getSharedPreferences("cache", Context.MODE_PRIVATE);
    }

    public static CacheDaoSharedPreferences get() {
        return SingletonWeakManager.get().getInstance(CacheDaoSharedPreferences.class, new Function1<Class<CacheDaoSharedPreferences>, CacheDaoSharedPreferences>() {
            @Override
            public CacheDaoSharedPreferences invoke(Class<CacheDaoSharedPreferences> cacheDaoSharedPreferencesClass) {
                return new CacheDaoSharedPreferences();
            }
        });
    }

    @Override
    public Cache getCache(String key) {
        String value = sp().getString(key, "");
        return new Cache(key, value.getBytes());
    }

    @Override
    public boolean deleteCache(Cache cache) {
        return deleteCache(cache.getKey());
    }

    @Override
    public boolean deleteCache(String key) {
        if (!sp().contains(key)) {
            return true;
        }
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                sp().edit().remove(key).apply();
            } else {
                sp().edit().remove(key).commit();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean saveCache(Cache cache) {
        byte[] data = cache.getData();
        if (data == null || data.length == 0) {
            return true;
        }
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                sp().edit().putString(cache.getKey(), new String(data)).apply();
            } else {
                sp().edit().putString(cache.getKey(), new String(data)).commit();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
