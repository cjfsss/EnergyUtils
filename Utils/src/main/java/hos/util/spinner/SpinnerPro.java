package hos.util.spinner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;




import java.util.List;

import hos.util.adapter.ArrayAdapterPro;

/**
 * <p>Title: AppCompatSpinnerPro </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2022/4/28 13:24
 */
public class SpinnerPro extends Spinner {

    public SpinnerPro(Context context) {
        super(context);
    }

    public SpinnerPro(Context context, int mode) {
        super(context, mode);
    }

    public SpinnerPro(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SpinnerPro(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SpinnerPro(Context context, AttributeSet attrs, int defStyleAttr, int mode) {
        super(context, attrs, defStyleAttr, mode);
    }

    public <T> SpinnerPro bind( List<T> objects, int resource) {
        return bind(objects, resource, 0);
    }

    public <T> SpinnerPro bind( T[] objects, int resource) {
        return bind(objects, resource, 0);
    }

    public <T> SpinnerPro bind( List<T> objects, int resource,  int color) {
        ArrayAdapterPro<T> adapterPro = new ArrayAdapterPro<>(getContext(), resource, objects);
        adapterPro.setTextColor(color);
        setAdapter(adapterPro);
        return this;
    }

    public <T> SpinnerPro bind( T[] objects, int resource,  int color) {
        ArrayAdapterPro<T> adapterPro = new ArrayAdapterPro<>(getContext(), resource, objects);
        adapterPro.setTextColor(color);
        setAdapter(adapterPro);
        return this;
    }

    public <T> void setList( List<T> objects) {
        SpinnerAdapter spinnerAdapter = getAdapter();
        if (spinnerAdapter instanceof ArrayAdapterPro) {
            ArrayAdapterPro<T> adapter = (ArrayAdapterPro<T>) getAdapter();
            adapter.setList(objects);
        }
    }

    public <T> void setList( T[] objects) {
        SpinnerAdapter spinnerAdapter = getAdapter();
        if (spinnerAdapter instanceof ArrayAdapterPro) {
            ArrayAdapterPro<T> adapter = (ArrayAdapterPro<T>) getAdapter();
            adapter.setList(objects);
        }
    }

    public void setOnItemClickListener( OnItemClickListener listener) {
        if (listener == null) {
            setOnItemSelectedListener(null);
            return;
        }
        setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listener.onItemClick(SpinnerPro.this, getSelectedItem(), position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public interface OnItemClickListener {
        void onItemClick(SpinnerPro spinner, Object item, int position);
    }
}
