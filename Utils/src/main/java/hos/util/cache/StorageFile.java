package hos.util.cache;

import android.util.Base64;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * <p>Title: FileStorage </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2022/4/2 18:17
 */
public class StorageFile {
    public static <T> boolean saveCache(@NonNull String key, T body) {
        byte[] bytes = CacheUtils.toByteArray(body);
        byte[] encode = Base64.encode(bytes, Base64.NO_WRAP);
        Cache cache = new Cache(key, encode);
        CacheDao cacheDao = new CacheDaoFile();
        return cacheDao.saveCache(cache);
    }


    @SuppressWarnings("unchecked")
    @Nullable
    public static <T> T getCache(@NonNull String key) {
        CacheDao cacheDao = new CacheDaoFile();
        Cache cache = cacheDao.getCache(key);
        if (cache == null) {
            return null;
        }
        byte[] decode = Base64.decode(cache.getData(), Base64.NO_WRAP);
        return (T) CacheUtils.toObject(decode);
    }

    public static boolean deleteCache(@NonNull Cache cache) {
        CacheDao cacheDao = new CacheDaoFile();
        return cacheDao.deleteCache(cache);
    }

    public static boolean deleteCache(@NonNull String key) {
        CacheDao cacheDao = new CacheDaoFile();
        return cacheDao.deleteCache(key);
    }

}
