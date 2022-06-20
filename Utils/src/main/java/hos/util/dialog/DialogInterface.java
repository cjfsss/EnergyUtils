package hos.util.dialog;


import hos.util.utils.ResUtils;

/**
 * <p>Title: ProgressDialogListener </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2022/4/14 22:49
 */
public interface DialogInterface<P extends DialogInterface<P>> {

    void setCancelable(boolean cancelable);

    P setStyle(int style);

    default P setTitle( int title) {
        return setTitle(ResUtils.getString(title));
    }

    P setTitle(CharSequence title);

    P setContent(CharSequence content);

    default P setContent( int contentId) {
        return setContent(ResUtils.getString(contentId));
    }

    P applyBottom();

    P create();

    boolean isShowing();

    void show();

    void dismiss();

    void setProgress(int value);
}
