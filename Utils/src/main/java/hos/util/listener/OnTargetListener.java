package hos.util.listener;

import androidx.annotation.NonNull;

/**
 * <p>Title: OnTargetListener </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2021/5/7 23:30
 */
public interface OnTargetListener<TARGET> {

    void onTarget(@NonNull TARGET target);
}
