package hos.util.dialog;

import android.widget.ListAdapter;

import hos.util.listener.OnTargetListener;

/**
 * <p>Title: ChoiceDialogImpl </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2022/4/28 22:33
 */
public abstract class ChoiceDialogImpl<P extends ChoiceDialogImpl<P>> extends DialogImpl<P> implements ChoiceDialogInterface<P> {
    protected int itemId;
    protected CharSequence[] items;
    protected ListAdapter adapter;
    protected int checkedPosition;
    protected int[] checkedPositions;
    protected OnItemDialogListener<P> listener;

    protected CharSequence confirm;
    protected CharSequence cancel;
    protected OnTargetListener<P> confirmFunction;
    protected OnTargetListener<P> cancelFunction;

    @Override
    public P setItem(int itemId) {
        this.itemId = itemId;
        return (P) this;
    }

    @Override
    public P setItem(CharSequence[] items) {
        this.items = items;
        return (P) this;
    }

    @Override
    public P setAdapter(ListAdapter adapter) {
        this.adapter = adapter;
        return (P) this;
    }

    @Override
    public P setCheckedPosition(int checkedPosition) {
        this.checkedPosition = checkedPosition;
        return (P) this;
    }

    @Override
    public P setCheckedPosition(int[] checkedPositions) {
        this.checkedPositions = checkedPositions;
        return (P) this;
    }

    @Override
    public P setOnItemDialogListener(OnItemDialogListener<P> listener) {
        this.listener = listener;
        return (P) this;
    }

    @Override
    public P setConfirm(CharSequence confirm, OnTargetListener<P> confirmFunction) {
        this.confirm = confirm;
        this.confirmFunction = confirmFunction;
        return (P) this;
    }

    @Override
    public P setCancel(CharSequence cancel, OnTargetListener<P> cancelFunction) {
        this.cancel = cancel;
        this.cancelFunction = cancelFunction;
        return (P) this;
    }

    @Override
    @Deprecated
    public void setProgress(int value) {

    }

    public void clear() {
        listener = null;
        confirmFunction = null;
        cancelFunction = null;
    }
}
