package hos.util.dialog;



import hos.util.listener.OnTargetListener;
import hos.util.utils.ResUtils;

/**
 * <p>Title: AppCompatAlertDialog </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2022/4/23 21:34
 */
public interface TitleDialogInterface<P extends TitleDialogInterface<P>> extends DialogInterface<P> {

    P setOnCancelListener( final OnTargetListener<P> cancelListener);

    P setOnDismissListener( final OnTargetListener<P> dismissListener);

    P setConfirm(CharSequence confirm, final OnTargetListener<P> confirmFunction);

    default P setConfirm(final OnTargetListener<P> confirmFunction) {
        return setConfirm("确定", confirmFunction);
    }

    default P setCancel(final OnTargetListener<P> cancelFunction) {
        return setCancel("取消", cancelFunction);
    }

    default P setConfirm( int confirmId, final OnTargetListener<P> confirmFunction) {
        return setConfirm(ResUtils.getString(confirmId), confirmFunction);
    }

    P setCancel(CharSequence cancel, final OnTargetListener<P> cancelFunction);

    default P setCancel( int cancelId, final OnTargetListener<P> cancelFunction) {
        return setCancel(ResUtils.getString(cancelId), cancelFunction);
    }

    P setLeftButton(CharSequence left, final OnTargetListener<P> leftFunction);

    default P setLeftButton( int leftId, final OnTargetListener<P> leftFunction) {
        return setLeftButton(ResUtils.getString(leftId), leftFunction);
    }

    default P showTip(CharSequence title, final OnTargetListener<P> confirmFunction) {
        return setTitle(title).setConfirm(confirmFunction);
    }

    default P showTip( int title, final OnTargetListener<P> confirmFunction) {
        return setTitle(title).setConfirm(confirmFunction);
    }

    default P showContent(CharSequence title, CharSequence content, final OnTargetListener<P> confirmFunction) {
        return setTitle(title).setContent(content).setConfirm(confirmFunction);
    }

    default P showContent( int title,  int contentId, final OnTargetListener<P> confirmFunction) {
        return setTitle(title).setContent(contentId).setConfirm(confirmFunction);
    }

    default P showContent(CharSequence content, final OnTargetListener<P> confirmFunction) {
        return setTitle("温馨提示").setContent(content).setConfirm(confirmFunction);
    }

    default P showContent( int contentId, final OnTargetListener<P> confirmFunction) {
        return setTitle("温馨提示").setContent(contentId).setConfirm(confirmFunction);
    }

    default P showTip(CharSequence title, final OnTargetListener<P> confirmFunction, final OnTargetListener<P> cancelFunction) {
        return setTitle(title).setConfirm(confirmFunction).setCancel(cancelFunction);
    }

    default P showTip( int title, final OnTargetListener<P> confirmFunction, final OnTargetListener<P> cancelFunction) {
        return setTitle(title).setConfirm(confirmFunction).setCancel(cancelFunction);
    }

    default P showContent(CharSequence title, CharSequence content, final OnTargetListener<P> confirmFunction, final OnTargetListener<P> cancelFunction) {
        return setTitle(title).setContent(content).setConfirm(confirmFunction).setCancel(cancelFunction);
    }

    default P showContent( int title,  int contentId, final OnTargetListener<P> confirmFunction, final OnTargetListener<P> cancelFunction) {
        return setTitle(title).setContent(contentId).setConfirm(confirmFunction).setCancel(cancelFunction);
    }

    default P showContent(CharSequence content, final OnTargetListener<P> confirmFunction, final OnTargetListener<P> cancelFunction) {
        return setTitle("温馨提示").setContent(content).setConfirm(confirmFunction).setCancel(cancelFunction);
    }

    default P showContent( int contentId, final OnTargetListener<P> confirmFunction, final OnTargetListener<P> cancelFunction) {
        return setTitle("温馨提示").setContent(contentId).setConfirm(confirmFunction).setCancel(cancelFunction);
    }
}
