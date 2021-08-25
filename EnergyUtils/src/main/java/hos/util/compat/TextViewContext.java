package hos.util.compat;

import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.DigitsKeyListener;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.TextViewCompat;

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

    public static void setTextSizeRes(@DimenRes int id, @NonNull TextView... textView) {
        setTextSizeRes(null, id, textView);
    }

    public static void setTextSizeRes(@Nullable OnTargetListener<TextView> listener,
                                      @DimenRes int id, @NonNull TextView... textView) {
        ViewUtils.forTarget(new OnTargetListener<View>() {
            @Override
            public void onTarget(@NonNull View view) {
                TextView targetView = (TextView) view;
                targetView.setTextSize(TypedValue.COMPLEX_UNIT_PX, ResContext.getTextSize(view.getContext(), id));
                if (listener != null) {
                    listener.onTarget(targetView);
                }
            }
        }, textView);
    }

    public static void setTextSize(float size, @NonNull TextView... textView) {
        setTextSize(null, size, textView);
    }

    public static void setTextSize(@Nullable OnTargetListener<TextView> listener,
                                   float size, @NonNull TextView... textView) {
        ViewUtils.forTarget(new OnTargetListener<View>() {
            @Override
            public void onTarget(@NonNull View view) {
                TextView targetView = (TextView) view;
                targetView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
                if (listener != null) {
                    listener.onTarget(targetView);
                }
            }
        }, textView);
    }

    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView,
                                                                       @Nullable Drawable start, @Nullable Drawable top, @Nullable Drawable end,
                                                                       @Nullable Drawable bottom) {
        TextViewCompat.setCompoundDrawablesRelativeWithIntrinsicBounds(textView, start, top, end, bottom);
    }

    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView textView,
                                                                       @DrawableRes int start, @DrawableRes int top, @DrawableRes int end,
                                                                       @DrawableRes int bottom) {
        TextViewCompat.setCompoundDrawablesRelativeWithIntrinsicBounds(textView, start, top, end, bottom);
    }

    /**
     * Applies a tint to the image drawable.
     */
    public static void setImageTintListRes(@NonNull TextView view,
                                           @ColorRes int colorId) {
        TextViewCompat.setCompoundDrawableTintList(view,
                StateListContext.createColorStateList(view.getContext(), colorId));

    }

    /**
     * Applies a tint to the image drawable.
     */
    public static void setImageTintList(@NonNull TextView view,
                                        @ColorInt int colorId) {
        TextViewCompat.setCompoundDrawableTintList(view,
                StateListContext.createColorStateList(colorId));

    }

    /**
     * Applies a tint to the image drawable.
     */
    public static void setImageTintListRes(@NonNull TextView view,
                                           @ColorRes int normal, @ColorRes int pressed) {
        TextViewCompat.setCompoundDrawableTintList(view,
                StateListContext.createColorStateList(view.getContext(), normal, pressed));

    }

    /**
     * Applies a tint to the image drawable.
     */
    public static void setImageTintList(@NonNull TextView view,
                                        @ColorInt int normal, @ColorInt int pressed) {
        TextViewCompat.setCompoundDrawableTintList(view,
                StateListContext.createColorStateList(normal, pressed));

    }

    /**
     * Applies a tint to the image drawable.
     */
    public static void setImageTintListRes(@NonNull TextView view,
                                           @ColorRes int normal, @ColorRes int pressed, @ColorRes int unable) {
        TextViewCompat.setCompoundDrawableTintList(view,
                StateListContext.createColorStateList(view.getContext(), normal, pressed, unable));

    }

    /**
     * Applies a tint to the image drawable.
     */
    public static void setImageTintList(@NonNull TextView view,
                                        @ColorInt int normal, @ColorInt int pressed, @ColorInt int unable) {
        TextViewCompat.setCompoundDrawableTintList(view,
                StateListContext.createColorStateList(normal, pressed, unable));

    }

    /**
     * Applies a tint to the image drawable.
     */
    public static void setImageTintListRes(@NonNull TextView view,
                                           @ColorRes int normal, @ColorRes int pressed, @ColorRes int focused, @ColorRes int unable) {
        TextViewCompat.setCompoundDrawableTintList(view,
                StateListContext.createColorStateList(view.getContext(), normal, pressed, focused, unable));

    }

    /**
     * Applies a tint to the image drawable.
     */
    public static void setImageTintList(@NonNull TextView view,
                                        @ColorInt int normal, @ColorInt int pressed, @ColorInt int focused, @ColorInt int unable) {
        TextViewCompat.setCompoundDrawableTintList(view,
                StateListContext.createColorStateList(normal, pressed, focused, unable));

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

    public static void setNumberWord(@Nullable TextView... textView) {
        setNumberWord(null, textView);
    }

    /**
     * 设置输入内容只能为数字或者字母
     */
    @SuppressWarnings("SpellCheckingInspection")
    public static void setNumberWord(@Nullable OnTargetListener<TextView> listener, @Nullable TextView... textView) {
        ViewUtils.forTarget(new OnTargetListener<View>() {
            @Override
            public void onTarget(@NonNull View view) {
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

    public static void clearText(@Nullable TextView... textView) {
        clearText(null, textView);
    }

    public static void clearText(@Nullable OnTargetListener<TextView> listener, @Nullable TextView... textView) {
        ViewUtils.forTarget(new OnTargetListener<View>() {
            @Override
            public void onTarget(@NonNull View view) {
                TextView targetView = (TextView) view;
                targetView.setText("");
                if (listener != null) {
                    listener.onTarget(targetView);
                }
            }
        }, textView);
    }

    public static void clearHint(@Nullable TextView... textView) {
        clearHint(null, textView);
    }

    public static void clearHint(@Nullable OnTargetListener<TextView> listener, @Nullable TextView... textView) {
        ViewUtils.forTarget(new OnTargetListener<View>() {
            @Override
            public void onTarget(@NonNull View view) {
                TextView targetView = (TextView) view;
                targetView.setHint("");
                if (listener != null) {
                    listener.onTarget(targetView);
                }
            }
        }, textView);
    }
}
