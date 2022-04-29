package hos.util.dialog;

import android.widget.ListAdapter;

import androidx.annotation.ArrayRes;
import androidx.annotation.StringRes;

import hos.util.listener.OnTargetListener;
import hos.util.utils.ResUtils;

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

    P setConfirm(CharSequence confirm, final OnTargetListener<P> confirmFunction);

    default P setConfirm(@StringRes int confirmId, final OnTargetListener<P> confirmFunction) {
        return setConfirm(ResUtils.getString(confirmId), confirmFunction);
    }

    P setCancel(CharSequence cancel, final OnTargetListener<P> cancelFunction);

    default P setCancel(@StringRes int cancelId, final OnTargetListener<P> cancelFunction) {
        return setCancel(ResUtils.getString(cancelId), cancelFunction);
    }

    void showSingle();

    void showMulti();

    void showItem();

    default P setConfirm(final OnTargetListener<P> confirmFunction) {
        return setConfirm("确定", confirmFunction);
    }

    default P setCancel(final OnTargetListener<P> cancelFunction) {
        return setCancel("取消", cancelFunction);
    }
}
