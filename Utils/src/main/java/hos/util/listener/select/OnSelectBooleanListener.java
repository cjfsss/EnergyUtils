package hos.util.listener.select;




/**
 * <p>Title: OnSelectListener </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/9/27 10:59
 */
public interface OnSelectBooleanListener<V> extends OnSelectListener<V, Boolean> {

    @Override
    void onSelect( V target,  Boolean data, int position);
}
