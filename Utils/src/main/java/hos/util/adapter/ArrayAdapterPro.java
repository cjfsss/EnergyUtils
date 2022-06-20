package hos.util.adapter;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;




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

    public ArrayAdapterPro( Context context, int resource) {
        super(context, resource);
    }

    public ArrayAdapterPro( Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public ArrayAdapterPro( Context context, int resource,  T[] objects) {
        super(context, resource, objects);
    }

    public ArrayAdapterPro( Context context, int resource, int textViewResourceId,  T[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public ArrayAdapterPro( Context context, int resource,  List<T> objects) {
        super(context, resource, objects);
    }

    public ArrayAdapterPro( Context context, int resource, int textViewResourceId,  List<T> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    public void setTextColor(int mTextColor) {
        this.mTextColor = mTextColor;
    }

    public void setList( List<T> objects) {
        clear();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            addAll(objects);
        } else {
            for (T t : objects) {
                add(t);
            }
        }
    }

    public void setList( T[] objects) {
        clear();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            addAll(objects);
        } else {
            for (T t : objects) {
                add(t);
            }
        }
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        View target = super.getView(position, convertView, parent);
        if (target instanceof TextView) {
            ((TextView) target).setTextColor(mTextColor);
        }
        return target;
    }

    @Override
    public View getDropDownView(int position,  View convertView,  ViewGroup parent) {
        View target = super.getDropDownView(position, convertView, parent);
        if (target instanceof TextView) {
            ((TextView) target).setTextColor(mTextColor);
        }
        return target;
    }
}
