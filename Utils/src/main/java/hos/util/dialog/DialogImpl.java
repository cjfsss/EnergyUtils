package hos.util.dialog;

/**
 * <p>Title: P </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2022/4/25 22:19
 */
public abstract class DialogImpl<P extends DialogImpl<P>> implements DialogInterface<P> {
    protected CharSequence title;
    protected CharSequence content;
    protected int style;
    protected boolean cancelable = true;
    protected boolean applyBottom = false;

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
    public P setTitle(CharSequence title) {
        this.title = title;
        return (P) this;
    }

    @Override
    public P setContent(CharSequence content) {
        this.content = content;
        return (P) this;
    }

    @Override
    public P applyBottom() {
        applyBottom = true;
        return (P) this;
    }

}
