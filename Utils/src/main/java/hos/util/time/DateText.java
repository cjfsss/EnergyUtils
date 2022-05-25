package hos.util.time;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Calendar;

import hos.util.compat.CalendarCompat;
import hos.util.compat.CalendarConvert;
import hos.util.compat.CalendarFormat;
import hos.util.toast.ToastX;


/**
 * <p>Title: DateText </p>
 * <p>Description: 日期选择 </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2021/11/12 10:28
 */
public class DateText extends TimeView {

    public DateText(@NonNull Context context) {
        super(context);
    }

    public DateText(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DateText(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 显示日期选择对话框
     *
     * @param time   Long 目标时间
     * @param format String 格式化
     * @param isFull Boolean 是否选择全部时间
     */
    @Override
    public void show(Long time, String format, boolean isFull) {
        Object tag = getTag();
        if (tag == null) {
            tag = time;
        }
        Calendar calendar = CalendarCompat.fromMillisecondsSinceEpoch(
                Long.parseLong(String.valueOf(tag))
        );
        TimePickerDialog.OnTimeSetListener timeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                setText(CalendarConvert.calendar2String(calendar, format));
                setTag(calendar.getTimeInMillis());
                if (mOnTimeListener != null) {
                    mOnTimeListener.onTime(DateText.this, calendar.getTimeInMillis());
                }
            }
        };
        DatePickerDialog.OnDateSetListener dateListener =
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        if (isThisDay()) {
                            if (calendar.get(Calendar.YEAR) != year) {
                                ToastX.showShort("请选择当天的日期");
                                return;
                            }
                            if (calendar.get(Calendar.MONTH) != month) {
                                ToastX.showShort("请选择当天的日期");
                                return;
                            }
                            if (calendar.get(Calendar.DAY_OF_MONTH) != dayOfMonth) {
                                ToastX.showShort("请选择当天的日期");
                                return;
                            }
                        }
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        if (isFull || isTime(format)) {
                            TimePickerDialog timeDialog = new TimePickerDialog(
                                    getContext(), timeListener,
                                    calendar.get(Calendar.HOUR_OF_DAY), calendar.get(
                                    Calendar.MINUTE
                            ), true);
                            timeDialog.show();
                            return;
                        }
                        setText(CalendarConvert.calendar2String(calendar, format));
                        setTag(calendar.getTimeInMillis());
                        if (mOnTimeListener != null) {
                            mOnTimeListener.onTime(DateText.this, calendar.getTimeInMillis());
                        }
                    }
                };
        new DatePickerDialog(
                getContext(), dateListener,  // 初始化为当前日期
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        ).show();
    }


}
