package hos.util.cache;

import android.os.Parcelable;
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
        if (body == null) {
            return true;
        }
        byte[] bytes;
        if (body instanceof Parcelable) {
            bytes = CacheUtils.parcelable2Bytes((Parcelable) body);
        } else {
            bytes = CacheUtils.toByteArray(body);
        }
        if (bytes == null || bytes.length == 0) {
            return true;
        }
        byte[] encode = Base64.encode(bytes, Base64.NO_WRAP);
        Cache cache = new Cache(key, encode);
        CacheDao cacheDao = CacheDaoFile.get();
        return cacheDao.saveCache(cache);
    }


    @Nullable
    public static <T> T getCache(@NonNull String key, final Parcelable.Creator<T> creator) {
        CacheDao cacheDao = CacheDaoFile.get();
        Cache cache = cacheDao.getCache(key);
        if (cache == null || cache.getData() == null) {
            return null;
        }
        byte[] decode = Base64.decode(cache.getData(), Base64.NO_WRAP);
        if (decode == null || decode.length == 0) {
            return null;
        }
        return CacheUtils.bytes2Parcelable(decode, creator);
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public static <T> T getCache(@NonNull String key) {
        CacheDao cacheDao = CacheDaoFile.get();
        Cache cache = cacheDao.getCache(key);
        if (cache == null || cache.getData() == null) {
            return null;
        }
        byte[] decode = Base64.decode(cache.getData(), Base64.NO_WRAP);
        if (decode == null || decode.length == 0) {
            return null;
        }
        return (T) CacheUtils.toObject(decode);
    }

    public static boolean deleteCache(@NonNull Cache cache) {
        CacheDao cacheDao = CacheDaoFile.get();
        return cacheDao.deleteCache(cache);
    }

    public static boolean deleteCache(@NonNull String key) {
        CacheDao cacheDao = CacheDaoFile.get();
        return cacheDao.deleteCache(key);
    }

    public static boolean clear() {
        return CacheUtils.clearCache();
    }

}
