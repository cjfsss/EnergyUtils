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
public interface OnSelectDoubleListener<V> extends OnSelectListener<V, Double> {

    @Override
    void onSelect( V target,  Double data, int position);
}
