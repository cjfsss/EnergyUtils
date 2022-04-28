package hos.util.dialog;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;

/**
 * <p>Title: ProgressDialogListener </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2022/4/14 22:49
 */
public interface WindowDialogInterface<P extends WindowDialogInterface<P>> {

    void setCancelable(boolean cancelable);

    P setStyle(@StyleRes int style);

    P setContentView(@LayoutRes int layoutResID);

    P setContentView(@NonNull View view);

    P setContentView(@NonNull View view, @Nullable ViewGroup.LayoutParams params);

    P create();

    boolean isShowing();

    void show();

    void dismiss();
}
