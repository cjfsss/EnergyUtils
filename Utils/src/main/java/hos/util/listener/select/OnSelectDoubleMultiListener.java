package hos.util.listener.select;



import java.util.List;

/**
 * <p>Title: OnSelectMultLevelListener </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/9/28 22:05
 */
public interface OnSelectDoubleMultiListener<V> extends OnSelectMultiListener<V, Double> {

    void onSelect(V target,  List<Double> selectList,  List<Integer> selectPositionList);
}
