package hos.util.singleton;




import hos.util.func.Function1;

/**
 * <p>Title: ISingletonManager </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/1/2 15:55
 */
public interface ISingletonManager {

    /**
     * 单例，弱引用
     */

    <P extends ISingletonWrapper> P getInstance( final Class<P> key,
                                                 final Function1<Class<P>, P> function);

}
