package hos.util.dialog;

import androidx.annotation.Nullable;

import hos.util.listener.OnTargetListener;

/**
 * <p>Title: TitleDialogImpl </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2022/4/28 22:05
 */
public abstract class TitleDialogImpl<P extends TitleDialogImpl<P>> extends DialogImpl<P> implements TitleDialogInterface<P> {
    protected OnTargetListener<P> cancelListener;
    protected OnTargetListener<P> dismissListener;
    protected CharSequence confirm;
    protected CharSequence cancel;
    protected CharSequence left;
    protected OnTargetListener<P>  confirmFunction;
    protected OnTargetListener<P>  cancelFunction;
    protected OnTargetListener<P>  leftFunction;

    @Override
    public P setOnCancelListener(@Nullable OnTargetListener<P> cancelListener) {
        this.cancelListener = cancelListener;
        return (P) this;
    }

    @Override
    public P setOnDismissListener(@Nullable OnTargetListener<P> dismissListener) {
        this.dismissListener = dismissListener;
        return (P) this;
    }

    @Override
    public P setConfirm(CharSequence confirm, OnTargetListener<P>  confirmFunction) {
        this.confirm = confirm;
        this.confirmFunction = confirmFunction;
        return (P) this;
    }

    @Override
    public P setCancel(CharSequence cancel, OnTargetListener<P>  cancelFunction) {
        this.cancel = cancel;
        this.cancelFunction = cancelFunction;
        return (P) this;
    }

    @Override
    public P setLeftButton(CharSequence left, OnTargetListener<P>  leftFunction) {
        this.left = left;
        this.leftFunction = leftFunction;
        return (P) this;
    }


    @Override
    @Deprecated
    public void setProgress(int value) {

    }

    public void clear() {
        cancelListener = null;
        dismissListener = null;
        confirmFunction = null;
        cancelFunction = null;
        leftFunction = null;
    }
}
