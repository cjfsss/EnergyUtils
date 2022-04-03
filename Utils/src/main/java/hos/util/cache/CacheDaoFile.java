package hos.util.cache;

import java.io.File;

/**
 * <p>Title: CacheDaoImpl </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2022/4/2 17:49
 */
public class CacheDaoFile implements CacheDao {

    @Override
    public Cache getCache(String key) {
        File cacheFile = CacheUtils.getCacheFile(key);
        byte[] bytes = CacheUtils.readFile2BytesByStream(cacheFile);
        return new Cache(key, bytes);
    }

    @Override
    public boolean deleteCache(Cache cache) {
        File cacheFile = CacheUtils.getCacheFile(cache);
        return CacheUtils.deleteFile(cacheFile);
    }

    @Override
    public boolean deleteCache(String key) {
        File cacheFile = CacheUtils.getCacheFile(key);
        return CacheUtils.deleteFile(cacheFile);
    }

    @Override
    public boolean saveCache(Cache cache) {
        File cacheFile = CacheUtils.getCacheFile(cache);
        return CacheUtils.writeFileFromBytesByStream(cacheFile,cache.getData());
    }
}
