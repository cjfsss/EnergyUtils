package hos.util.dialog;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * <p>Title: WindowDialogImpl </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2022/4/29 0:11
 */
public abstract class WindowDialogImpl<P extends WindowDialogImpl<P>> implements WindowDialogInterface<P> {
    protected int style;
    protected boolean cancelable = true;
    protected View view;
    protected int layoutId;
    protected ViewGroup.LayoutParams params;

    @Override
    public void setCancelable(boolean cancelable) {
        this.cancelable = cancelable;
    }

    @Override
    public P setStyle(int style) {
        this.style = style;
        return (P) this;
    }

    @Override
    public P setContentView(int layoutResID) {
        this.layoutId = layoutResID;
        return (P) this;
    }

    @Override
    public P setContentView(@NonNull  View view) {
        this.view = view;
        return (P) this;
    }

    @Override
    public P setContentView(@NonNull  View view, @Nullable  ViewGroup.LayoutParams params) {
        this.view = view;
        this.params = params;
        return (P) this;
    }

}
