package hos.util.listener.select;

import androidx.annotation.NonNull;

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
public interface OnSelectBooleanMultiLevelListener<V> extends OnSelectMultiLevelListener<V, Boolean> {

    void onSelect(V target, @NonNull List<Boolean> selectList, @NonNull List<Integer> selectPositionList, int level);
}
