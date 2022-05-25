package hos.util.cache;

import java.io.File;

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
class CacheDaoFile implements CacheDao, ISingletonWrapper {

    public static CacheDaoFile get() {
        return SingletonWeakManager.get().getInstance(CacheDaoFile.class, new Function1<Class<CacheDaoFile>, CacheDaoFile>() {
            @Override
            public CacheDaoFile invoke(Class<CacheDaoFile> cacheDao) {
                return new CacheDaoFile();
            }
        });
    }

    @Override
    public Cache getCache(String key) {
        try {
            File cacheFile = CacheUtils.getCacheFile(key);
            if (!CacheUtils.isFileExists(cacheFile)) {
                return null;
            }
            byte[] bytes = CacheUtils.readFile2BytesByStream(cacheFile);
            if (bytes == null || bytes.length == 0) {
                return null;
            }
            return new Cache(key, bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteCache(Cache cache) {
        return deleteCache(cache.getKey());
    }

    @Override
    public boolean deleteCache(String key) {
        try {
            File cacheFile = CacheUtils.getCacheFile(key);
            return CacheUtils.deleteFile(cacheFile);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean saveCache(Cache cache) {
        try {
            File cacheFile = CacheUtils.getCacheFile(cache);
            return CacheUtils.writeFileFromBytesByStream(cacheFile, cache.getData());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean clear() {
        try {
            return CacheUtils.clearCache();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
