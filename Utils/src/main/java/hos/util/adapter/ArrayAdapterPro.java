package hos.util.adapter;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/**
 * <p>Title: AppCompatArrayAdapter </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2022/4/28 13:26
 */
public class ArrayAdapterPro<T> extends ArrayAdapter<T> {

    private int mTextColor;

    public ArrayAdapterPro(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public ArrayAdapterPro(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public ArrayAdapterPro(@NonNull Context context, int resource, @NonNull T[] objects) {
        super(context, resource, objects);
    }

    public ArrayAdapterPro(@NonNull Context context, int resource, int textViewResourceId, @NonNull T[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public ArrayAdapterPro(@NonNull Context context, int resource, @NonNull List<T> objects) {
        super(context, resource, objects);
    }

    public ArrayAdapterPro(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<T> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public void setTextColor(int mTextColor) {
        this.mTextColor = mTextColor;
    }

    public void setList(@NonNull List<T> objects) {
        clear();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            addAll(objects);
        } else {
            for (T t : objects) {
                add(t);
            }
        }
    }

    public void setList(@NonNull T[] objects) {
        clear();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            addAll(objects);
        } else {
            for (T t : objects) {
                add(t);
            }
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View target = super.getView(position, convertView, parent);
        if (target instanceof TextView) {
            ((TextView) target).setTextColor(mTextColor);
        }
        return target;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View target = super.getDropDownView(position, convertView, parent);
        if (target instanceof TextView) {
            ((TextView) target).setTextColor(mTextColor);
        }
        return target;
    }
}
