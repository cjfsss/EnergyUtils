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
public interface OnSelectIntegerMultiListener<V> extends OnSelectMultiListener<V, Integer> {

    void onSelect(V target,  List<Integer> selectList,  List<Integer> selectPositionList);
}
