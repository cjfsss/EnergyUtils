package hos.util.dialog;

/**
 * <p>Title: OnItemDialogListener </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2022/4/28 22:19
 */
public interface OnItemDialogListener<P extends DialogInterface<P>> {

    void onItemSelected(P target, Object item, int position, boolean isChecked);

}
