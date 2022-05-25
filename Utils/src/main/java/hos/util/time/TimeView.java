package hos.util.time;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import hos.util.compat.CalendarConvert;
import hos.util.compat.CalendarFormat;


/**
 * <p>Title: DateText </p>
 * <p>Description: 日期选择 </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2021/11/12 10:28
 */
public abstract class TimeView extends TextView {

    private String format = CalendarFormat.full;
    private boolean isFull = true;
    private boolean isThisDay = false;
    protected OnTimeListener mOnTimeListener;

    public TimeView(@NonNull Context context) {
        this(context, null);
    }

    public TimeView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Object tag = getTag();
                if (tag == null) {
                    tag = CalendarConvert.getNowMills();
                }
                show(Long.parseLong(String.valueOf(tag)), format, isFull);
            }
        });
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setFull(boolean full) {
        isFull = full;
    }
    public boolean isThisDay() {
        return isThisDay;
    }

    public void setThisDay(boolean thisDay) {
        isThisDay = thisDay;
    }
    /**
     * 显示日期选择对话框
     */
    public void show() {
        show(CalendarConvert.getNowMills(), CalendarFormat.full, true);
    }

    /**
     * 显示日期选择对话框
     */
    public void show(Long time, String format) {
        show(time, format, true);
    }

    /**
     * 显示日期选择对话框
     */
    public void show(String format, boolean isFull) {
        show(CalendarConvert.getNowMills(), format, isFull);
    }

    /**
     * 显示日期选择对话框
     *
     * @param time   Long 目标时间
     * @param format String 格式化
     * @param isFull Boolean 是否选择全部时间
     */
    public abstract void show(Long time, String format, boolean isFull);

    public final void setText(CharSequence text, String format) {
        this.format = format;
        setText(text);
        setTag(CalendarConvert.string2Millis(String.valueOf(text), format));
    }

    public String getTimeFull() {
        Object tag = getTag();
        if (tag != null) {
            return CalendarConvert.millis2String((Long) tag);
        }
        CharSequence text = getText();
        return CalendarConvert.millis2String(CalendarConvert.string2Millis(String.valueOf(text), format));
    }

    public void setOnTimeListener(OnTimeListener mOnTimeListener) {
        this.mOnTimeListener = mOnTimeListener;
    }

    public static interface OnTimeListener {
        void onTime(TimeView dateText, long millis);
    }

    public static boolean isTime(String format) {
        if (TextUtils.equals(format, CalendarFormat.full)) {
            return true;
        }
        if (TextUtils.equals(format, CalendarFormat.y_mo_d_h_m)) {
            return true;
        }
        if (TextUtils.equals(format, CalendarFormat.y_mo_d_h)) {
            return true;
        }
        if (TextUtils.equals(format, CalendarFormat.zh_full)) {
            return true;
        }
        if (TextUtils.equals(format, CalendarFormat.zh_y_mo_d_h_m)) {
            return true;
        }
        if (TextUtils.equals(format, CalendarFormat.zh_y_mo_d_h)) {
            return true;
        }
        return false;
    }
}
