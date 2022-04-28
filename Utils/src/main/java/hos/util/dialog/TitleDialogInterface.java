package hos.util.dialog;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

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

    P setOnCancelListener(@Nullable final OnTargetListener<P> cancelListener);

    P setOnDismissListener(@Nullable final OnTargetListener<P> dismissListener);

    P setConfirm(CharSequence confirm, final OnTargetListener<P> confirmFunction);

    default P setConfirm(@StringRes int confirmId, final OnTargetListener<P> confirmFunction) {
        return setConfirm(ResUtils.getString(confirmId), confirmFunction);
    }

    P setCancel(CharSequence cancel, final OnTargetListener<P> cancelFunction);

    default P setCancel(@StringRes int cancelId, final OnTargetListener<P> cancelFunction) {
        return setCancel(ResUtils.getString(cancelId), cancelFunction);
    }

    P setLeftButton(CharSequence left, final OnTargetListener<P> leftFunction);

    default P setLeftButton(@StringRes int leftId, final OnTargetListener<P> leftFunction) {
        return setLeftButton(ResUtils.getString(leftId), leftFunction);
    }

    default P showTip(CharSequence title, final OnTargetListener<P> confirmFunction) {
        return setTitle(title).setConfirm("确定", confirmFunction);
    }

    default P showTip(@StringRes int title, final OnTargetListener<P> confirmFunction) {
        return setTitle(title).setConfirm("确定", confirmFunction);
    }

    default P showContent(CharSequence title, CharSequence content, final OnTargetListener<P> confirmFunction) {
        return setTitle(title).setContent(content).setConfirm("确定", confirmFunction);
    }

    default P showContent(@StringRes int title, @StringRes int contentId, final OnTargetListener<P> confirmFunction) {
        return setTitle(title).setContent(contentId).setConfirm("确定", confirmFunction);
    }

    default P showContent(CharSequence content, final OnTargetListener<P> confirmFunction) {
        return setTitle("温馨提示").setContent(content).setConfirm("确定", confirmFunction);
    }

    default P showContent(@StringRes int contentId, final OnTargetListener<P> confirmFunction) {
        return setTitle("温馨提示").setContent(contentId).setConfirm("确定", confirmFunction);
    }

    default P showTip(CharSequence title, final OnTargetListener<P> confirmFunction, final OnTargetListener<P> cancelFunction) {
        return setTitle(title).setConfirm("确定", confirmFunction).setCancel("取消", cancelFunction);
    }

    default P showTip(@StringRes int title, final OnTargetListener<P> confirmFunction, final OnTargetListener<P> cancelFunction) {
        return setTitle(title).setConfirm("确定", confirmFunction).setCancel("取消", cancelFunction);
    }

    default P showContent(CharSequence title, CharSequence content, final OnTargetListener<P> confirmFunction, final OnTargetListener<P> cancelFunction) {
        return setTitle(title).setContent(content).setConfirm("确定", confirmFunction).setCancel("取消", cancelFunction);
    }

    default P showContent(@StringRes int title, @StringRes int contentId, final OnTargetListener<P> confirmFunction, final OnTargetListener<P> cancelFunction) {
        return setTitle(title).setContent(contentId).setConfirm("确定", confirmFunction).setCancel("取消", cancelFunction);
    }

    default P showContent(CharSequence content, final OnTargetListener<P> confirmFunction, final OnTargetListener<P> cancelFunction) {
        return setTitle("温馨提示").setContent(content).setConfirm("确定", confirmFunction).setCancel("取消", cancelFunction);
    }

    default P showContent(@StringRes int contentId, final OnTargetListener<P> confirmFunction, final OnTargetListener<P> cancelFunction) {
        return setTitle("温馨提示").setContent(contentId).setConfirm("确定", confirmFunction).setCancel("取消", cancelFunction);
    }
}
