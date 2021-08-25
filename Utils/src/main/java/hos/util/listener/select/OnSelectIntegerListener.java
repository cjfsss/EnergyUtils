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
public interface OnSelectIntegerListener<V> extends OnSelectListener<V, Integer> {

    @Override
    void onSelect(@NonNull V target, @Nullable Integer data, int position);
}