package hos.util.dialog;

import android.view.View;


/**
 * <p>Title: ViewDialogInterface </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2022/4/29 0:03
 */
public interface ViewDialogInterface<P extends ViewDialogInterface<P>> extends DialogInterface<P> {

    P setView(View view);

    P setView(int layoutId);
}
