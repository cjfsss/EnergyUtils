package hos.util.dialog;

import android.view.View;

/**
 * <p>Title: ViewDialogImpl </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2022/4/29 0:05
 */
public abstract class ViewDialogImpl<P extends ViewDialogImpl<P>> extends DialogImpl<P> implements ViewDialogInterface<P> {
    protected View view;
    protected int layoutId;

    @Override
    public P setView(View view) {
        this.view = view;
        return (P) this;
    }

    @Override
    public P setView(int layoutId) {
        this.layoutId = layoutId;
        return (P) this;
    }

    @Override
    @Deprecated
    public void setProgress(int value) {

    }

    public void clear() {
        view = null;
    }
}
