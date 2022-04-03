package hos.util.cache;

/**
 * <p>Title: CacheDao </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2022/4/2 20:48
 */
public interface CacheDao {

    Cache getCache(String key);

    boolean deleteCache(Cache cache);

    boolean deleteCache(String key);

    boolean saveCache(Cache cache);
}
