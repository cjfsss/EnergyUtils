package hos.util.listener;



/**
 * <p>Title: IListener </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2019/12/23 23:10
 */
public interface IListener<LISTENER> {

    /**
     * 添加监听
     */
    void addListener( LISTENER listener);

    /**
     * 移除监听
     */
    void removeListener( LISTENER listener);

    /**
     * 移除所有监听
     */
    void removeAll();

}
