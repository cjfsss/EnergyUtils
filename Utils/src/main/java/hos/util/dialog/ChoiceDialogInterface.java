package hos.util.dialog;

import android.widget.ListAdapter;

import androidx.annotation.ArrayRes;

/**
 * <p>Title: AppCompatAlertChoseDialog </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2022/4/23 21:59
 */
public interface ChoiceDialogInterface<P extends ChoiceDialogInterface<P>> extends DialogInterface<P> {

    P setItem(@ArrayRes int itemId);

    P setItem(CharSequence[] items);

    P setAdapter(ListAdapter adapter);

    P setCheckedPosition(int checkedPosition);

    P setCheckedPosition(int[] checkedPositions);

    P setOnItemDialogListener(final OnItemDialogListener<P> listener);

    void showSingle();

    void showMulti();

    void showItem();
}
