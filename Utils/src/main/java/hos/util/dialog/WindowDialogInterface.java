package hos.util.dialog;

import android.view.View;
import android.view.ViewGroup;


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

    P setStyle( int style);

    P setContentView( int layoutResID);

    P setContentView( View view);

    P setContentView( View view,  ViewGroup.LayoutParams params);

    P create();

    boolean isShowing();

    void show();

    void dismiss();
}
