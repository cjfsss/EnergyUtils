package hos.util.listener.select;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * <p>Title: OnSelectListener </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/9/27 10:59
 */
public interface OnSelectListener<V, T> {

    void onSelect(@NonNull V target, @Nullable T data, int position);
}
