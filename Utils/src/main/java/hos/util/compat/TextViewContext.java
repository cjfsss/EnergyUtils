package hos.util.compat;

import android.text.InputType;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;




import hos.util.listener.OnTargetListener;
import hos.util.utils.StringUtils;
import hos.util.utils.ViewUtils;

/**
 * <p>Title: TextViewContext </p>
 * <p>Description: 文字 </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2021/2/27 21:25
 */
public final class TextViewContext {

    public static void setTextSizeRes( int id,  TextView... textView) {
        setTextSizeRes(null, id, textView);
    }

    public static void setTextSizeRes( OnTargetListener<TextView> listener,
                                       int id,  TextView... textView) {
        ViewUtils.forTarget(new OnTargetListener<View>() {
            @Override
            public void onTarget( View view) {
                TextView targetView = (TextView) view;
                targetView.setTextSize(TypedValue.COMPLEX_UNIT_PX, ResContext.getTextSize(view.getContext(), id));
                if (listener != null) {
                    listener.onTarget(targetView);
                }
            }
        }, textView);
    }

    public static void setTextSize(float size,  TextView... textView) {
        setTextSize(null, size, textView);
    }

    public static void setTextSize( OnTargetListener<TextView> listener,
                                   float size,  TextView... textView) {
        ViewUtils.forTarget(new OnTargetListener<View>() {
            @Override
            public void onTarget( View view) {
                TextView targetView = (TextView) view;
                targetView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
                if (listener != null) {
                    listener.onTarget(targetView);
                }
            }
        }, textView);
    }

    /**
     * 显示密码文本
     */
    public static void showPassword(EditText textView) {
        textView.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        textView.setSelection(textView.getText().length());
    }

    /**
     * 隐藏密码文本
     */
    public static void hidePassword(EditText textView) {
        textView.setTransformationMethod(PasswordTransformationMethod.getInstance());
        textView.setSelection(textView.getText().length());
    }

    public static void setNumberWord( TextView... textView) {
        setNumberWord(null, textView);
    }

    /**
     * 设置输入内容只能为数字或者字母
     */
    @SuppressWarnings("SpellCheckingInspection")
    public static void setNumberWord( OnTargetListener<TextView> listener,  TextView... textView) {
        ViewUtils.forTarget(new OnTargetListener<View>() {
            @Override
            public void onTarget( View view) {
                TextView targetView = (TextView) view;
                targetView.setKeyListener(
                        DigitsKeyListener.getInstance("0123456789qwertyuioplkjhgfdsazxcvbnmQWERTYUIOPLKJHGFDSAZXCVBNM"));
                targetView.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                if (listener != null) {
                    listener.onTarget(targetView);
                }
            }
        }, textView);
    }

    public static String getTextString(TextView textView) {
        if (TextUtils.isEmpty(textView.getText())) {
            return "";
        }
        return StringUtils.toEmpty(textView.getText().toString());
    }

    public static void clearText( TextView... textView) {
        clearText(null, textView);
    }

    public static void clearText( OnTargetListener<TextView> listener,  TextView... textView) {
        ViewUtils.forTarget(new OnTargetListener<View>() {
            @Override
            public void onTarget( View view) {
                TextView targetView = (TextView) view;
                targetView.setText("");
                if (listener != null) {
                    listener.onTarget(targetView);
                }
            }
        }, textView);
    }

    public static void clearHint( TextView... textView) {
        clearHint(null, textView);
    }

    public static void clearHint( OnTargetListener<TextView> listener,  TextView... textView) {
        ViewUtils.forTarget(new OnTargetListener<View>() {
            @Override
            public void onTarget( View view) {
                TextView targetView = (TextView) view;
                targetView.setHint("");
                if (listener != null) {
                    listener.onTarget(targetView);
                }
            }
        }, textView);
    }
}
