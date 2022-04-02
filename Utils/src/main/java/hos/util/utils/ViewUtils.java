package hos.util.utils;

import android.animation.Animator;
import android.animation.IntEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.atomic.AtomicInteger;

import hos.util.compat.TextViewContext;
import hos.util.listener.OnTargetListener;

/**
 * <p>Title: ViewUtils </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/1/8 15:29
 */
public class ViewUtils {

    private static final int KEY_OFFSET = -123;

    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    public static int generateViewId() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            return View.generateViewId();
        } else {
            for (; ; ) {
                final int result = sNextGeneratedId.get();
                // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
                int newValue = result + 1;
                if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
                if (sNextGeneratedId.compareAndSet(result, newValue)) {
                    return result;
                }
            }
        }
    }

    public static void forTarget(@Nullable OnTargetListener<View> listener, @Nullable View... views) {
        if (views == null) {
            return;
        }
        for (View view : views) {
            if (view == null) {
                continue;
            }
            if (listener != null) {
                listener.onTarget(view);
            }
        }
    }

    public static void setVisibility(int visibility, @Nullable View... views) {
        setVisibility(null, visibility, views);
    }

    public static void setVisibility(@Nullable OnTargetListener<View> listener, int visibility, @Nullable View... views) {
        forTarget(new OnTargetListener<View>() {
            @Override
            public void onTarget(@NonNull View view) {
                view.setVisibility(visibility);
                if (listener != null) {
                    listener.onTarget(view);
                }
            }
        }, views);
    }

    public static void gone(@Nullable View... views) {
        gone(null, views);
    }

    public static void gone(@Nullable OnTargetListener<View> listener, @Nullable View... views) {
        forTarget(new OnTargetListener<View>() {
            @Override
            public void onTarget(@NonNull View view) {
                view.setVisibility(View.GONE);
                if (listener != null) {
                    listener.onTarget(view);
                }
            }
        }, views);
    }

    public static void visible(@Nullable View... views) {
        visible(null, views);
    }

    public static void visible(@Nullable OnTargetListener<View> listener, @Nullable View... views) {
        forTarget(new OnTargetListener<View>() {
            @Override
            public void onTarget(@NonNull View view) {
                view.setVisibility(View.VISIBLE);
                if (listener != null) {
                    listener.onTarget(view);
                }
            }
        }, views);
    }

    public static void invisible(@Nullable View... views) {
        invisible(null, views);
    }

    public static void invisible(@Nullable OnTargetListener<View> listener, @Nullable View... views) {
        forTarget(new OnTargetListener<View>() {
            @Override
            public void onTarget(@NonNull View view) {
                view.setVisibility(View.INVISIBLE);
                if (listener != null) {
                    listener.onTarget(view);
                }
            }
        }, views);
    }

    public static void setEnabled(boolean enable, @Nullable View... views) {
        setEnabled(null, enable, views);
    }

    public static void setEnabled(@Nullable OnTargetListener<View> listener, boolean enable, @Nullable View... views) {
        forTarget(new OnTargetListener<View>() {
            @Override
            public void onTarget(@NonNull View view) {
                view.setFocusable(enable);
                view.setFocusableInTouchMode(enable);
                view.setEnabled(enable);
                if (enable) {
                    view.setAlpha(1f);
                } else {
                    view.setAlpha(0.5f);
                }
                if (listener != null) {
                    listener.onTarget(view);
                }
            }
        }, views);
    }

    /**
     * 设置输入内容只能为数字或者字母
     */
    public static void setNumberWord(@Nullable TextView... textView) {
        TextViewContext.setNumberWord(textView);
    }

    public static String getTextString(@Nullable TextView textView) {
        return TextViewContext.getTextString(textView);
    }

    public static void clearText(@Nullable TextView... textView) {
        TextViewContext.clearText(textView);
    }

    public static void clearHint(@Nullable TextView... textView) {
        TextViewContext.clearHint(textView);
    }

    public final static void clearClickListener(@Nullable View... views) {
        clearClickListener(null, views);
    }

    public final static void clearClickListener(@Nullable OnTargetListener<View> listener, @Nullable View... views) {
        forTarget(new OnTargetListener<View>() {
            @Override
            public void onTarget(@NonNull View view) {
                view.setOnClickListener(null);
                if (listener != null) {
                    listener.onTarget(view);
                }
            }
        }, views);
    }

    public final static void setOnClickListener(@Nullable View.OnClickListener onClick, @Nullable View... views) {
        if (views == null) {
            return;
        }
        for (View view : views) {
            if (view == null) {
                continue;
            }
            view.setOnClickListener(onClick);
        }
    }

    public final static void clearLongClickListener(@Nullable View... views) {
        if (views == null) {
            return;
        }
        for (View view : views) {
            if (view == null) {
                continue;
            }
            view.setOnLongClickListener(null);
        }
    }

    public final static void setOnLongClickListener(@Nullable View.OnLongClickListener onClick, @Nullable View... views) {
        if (views == null) {
            return;
        }
        for (View view : views) {
            if (view == null) {
                continue;
            }
            view.setOnLongClickListener(onClick);
        }
    }

    public static void toggleVisibility(@Nullable View... views) {
        if (views == null) {
            return;
        }
        for (View view : views) {
            if (view.getVisibility() == View.GONE) {
                view.setVisibility(View.VISIBLE);
            } else {
                view.setVisibility(View.GONE);
            }
        }
    }

    public static void setCommonPaddingTopBottom(@NonNull View view) {
        int leftRight = (int) dp2px(view.getContext(), 0);
        int topBottom = (int) dp2px(view.getContext(), 8);
        setPadding(view, leftRight, topBottom);
    }

    public static void setCommonPaddingLeftRight(@NonNull View view) {
        int leftRight = (int) dp2px(view.getContext(), 16);
        int topBottom = (int) dp2px(view.getContext(), 0);
        setPadding(view, leftRight, topBottom);
    }

    public static void setCommonPadding(@NonNull View view) {
        int leftRight = (int) dp2px(view.getContext(), 16);
        int topBottom = (int) dp2px(view.getContext(), 8);
        setPadding(view, leftRight, topBottom);
    }

    public static void setPadding(@NonNull View view, int leftRight, int topBottom) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            view.setPaddingRelative(leftRight, topBottom, leftRight, topBottom);
        } else {
            view.setPadding(leftRight, topBottom, leftRight, topBottom);
        }
    }

    public static float dp2px(@NonNull View view, float i) {
        return dp2px(view.getContext(), i);
    }

    public static float dp2px(@NonNull Context context, float i) {
        return (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, i, context.getResources().getDisplayMetrics());
    }

    public static float sp2px(@NonNull View view, float i) {
        return sp2px(view.getContext(), i);
    }

    public static float sp2px(@NonNull Context context, float i) {
        return (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_SP, i, context.getResources().getDisplayMetrics());
    }

    /**
     * Add the top margin size equals status bar's height for view.
     *
     * @param view The view.
     */
    public static void addMarginHeight(@NonNull View view, int left, int top, int right, int bottom) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }
        Object haveSetOffset = view.getTag(KEY_OFFSET);
        if (haveSetOffset != null && (Boolean) haveSetOffset) {
            return;
        }
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        layoutParams.setMargins(layoutParams.leftMargin + left, layoutParams.topMargin + top,
                layoutParams.rightMargin + right, layoutParams.bottomMargin + bottom);
        view.setTag(KEY_OFFSET, true);
    }

    public static void addMarginTopHeight(@NonNull View view, int top) {
        addMarginHeight(view, 0, top, 0, 0);
    }

    public static void addMarginBottomHeight(@NonNull View view, int bottom) {
        addMarginHeight(view, 0, 0, 0, bottom);
    }

    /**
     * 设置View的宽度和高度
     *
     * @param width  要设置的宽度
     * @param height 要设置的高度
     */
    public static View widthAndHeight(View view, int width, int height) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params == null) {
            params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        if (width != -3) {
            params.width = width;
        }
        if (height != -3) {
            params.height = height;
        }
        view.setLayoutParams(params);
        return view;
    }

    /**
     * 设置View的高度
     */
    public static View height(View view, int height) {
        return widthAndHeight(view, -3, height);
    }

    /**
     * 设置View的宽度
     */
    public static View width(View view, int width) {
        return widthAndHeight(view, width, -3);
    }

    /**
     * 设置View高度，限制在min和max范围之内
     *
     * @param h
     * @param min 最小高度
     * @param max 最大高度
     */
    public static View limitHeight(View view, int h, int min, int max) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params == null) {
            params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        if (h < min) {
            params.height = min;
        } else if (h > max) {
            params.height = max;
        } else {
            params.height = h;
        }
        view.setLayoutParams(params);
        return view;
    }

    /**
     * 设置View宽度，限制在min和max范围之内
     *
     * @param w
     * @param min 最小宽度
     * @param max 最大宽度
     */
    public static View limitWidth(View view, int w, int min, int max) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params == null) {
            params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        if (w < min) {
            params.width = min;
        } else if (w > max) {
            params.width = max;
        } else {
            params.width = w;
        }
        view.setLayoutParams(params);
        return view;
    }

    /**
     * 设置View的margin
     *
     * @param leftMargin   默认保留原来的
     * @param topMargin    默认是保留原来的
     * @param rightMargin  默认是保留原来的
     * @param bottomMargin 默认是保留原来的
     */
    public static View margin(View view, int leftMargin, int topMargin, int rightMargin, int bottomMargin) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        if (leftMargin != Integer.MAX_VALUE)
            params.leftMargin = leftMargin;
        if (topMargin != Integer.MAX_VALUE)
            params.topMargin = topMargin;
        if (rightMargin != Integer.MAX_VALUE)
            params.rightMargin = rightMargin;
        if (bottomMargin != Integer.MAX_VALUE)
            params.bottomMargin = bottomMargin;
        view.setLayoutParams(params);
        return view;
    }

    /**
     * 设置宽度，带有过渡动画
     *
     * @param targetValue 目标宽度
     */
    public static void animateWidth(@NonNull View view, int targetValue, @Nullable Animator.AnimatorListener listener) {
        animateWidth(view, targetValue, 400, listener, null);
    }

    /**
     * 设置宽度，带有过渡动画
     *
     * @param targetValue 目标宽度
     */
    public static void animateWidth(@NonNull View view, int targetValue, @Nullable ValueAnimator.AnimatorUpdateListener updateListener) {
        animateWidth(view, targetValue, 400, null, updateListener);
    }

    /**
     * 设置宽度，带有过渡动画
     *
     * @param targetValue    目标宽度
     * @param duration       时长
     * @param updateListener 可选行为
     */
    public static void animateWidth(@NonNull View view, int targetValue, long duration, @Nullable Animator.AnimatorListener listener,
                                    @Nullable ValueAnimator.AnimatorUpdateListener updateListener) {
        view.post(new Runnable() {
            @Override
            public void run() {
                ValueAnimator valueAnimator = ValueAnimator.ofInt(view.getWidth(), targetValue);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        width(view, (int) animation.getAnimatedFraction());
                        if (updateListener != null) {
                            updateListener.onAnimationUpdate(animation);
                        }
                    }
                });
                if (listener != null) {
                    valueAnimator.addListener(listener);
                }
                valueAnimator.setDuration(duration);
                valueAnimator.start();
            }
        });
    }

    /**
     * 设置高度，带有过渡动画
     *
     * @param targetValue 目标宽度
     */
    public static void animateHeight(@NonNull View view, int targetValue, @Nullable Animator.AnimatorListener listener) {
        animateHeight(view, targetValue, 400, listener, null);
    }

    /**
     * 设置高度，带有过渡动画
     *
     * @param targetValue 目标宽度
     */
    public static void animateHeight(@NonNull View view, int targetValue, @Nullable ValueAnimator.AnimatorUpdateListener updateListener) {
        animateHeight(view, targetValue, 400, null, updateListener);
    }

    /**
     * 设置高度，带有过渡动画
     *
     * @param targetValue    目标宽度
     * @param duration       时长
     * @param updateListener 可选行为
     */
    public static void animateHeight(@NonNull View view, int targetValue, long duration, @Nullable Animator.AnimatorListener listener,
                                     @Nullable ValueAnimator.AnimatorUpdateListener updateListener) {
        view.post(new Runnable() {
            @Override
            public void run() {
                ValueAnimator valueAnimator = ValueAnimator.ofInt(view.getHeight(), targetValue);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        height(view, (int) animation.getAnimatedFraction());
                        if (updateListener != null) {
                            updateListener.onAnimationUpdate(animation);
                        }
                    }
                });
                if (listener != null) {
                    valueAnimator.addListener(listener);
                }
                valueAnimator.setDuration(duration);
                valueAnimator.start();
            }
        });
    }

    /**
     * 设置宽度和高度，带有过渡动画
     *
     * @param targetWidth    目标宽度
     * @param targetHeight   目标高度
     * @param duration       时长
     * @param updateListener 可选行为
     */
    public static void animateWidthAndHeight(View view, int targetWidth, int targetHeight, long duration, @Nullable Animator.AnimatorListener listener,
                                             @Nullable ValueAnimator.AnimatorUpdateListener updateListener) {
        view.post(new Runnable() {
            @Override
            public void run() {
                int startHeight = view.getHeight();
                IntEvaluator evaluator = new IntEvaluator();
                ValueAnimator valueAnimator = ValueAnimator.ofInt(view.getWidth(), targetWidth);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        int animatedValue = (int) animation.getAnimatedFraction();
                        widthAndHeight(view, animatedValue, evaluator.evaluate(animatedValue, startHeight, targetHeight));
                        if (updateListener != null) {
                            updateListener.onAnimationUpdate(animation);
                        }
                    }
                });
                if (listener != null) {
                    valueAnimator.addListener(listener);
                }
                valueAnimator.setDuration(duration);
                valueAnimator.start();
            }
        });
    }

    /**
     * 获取View的截图, 支持获取整个RecyclerView列表的长截图
     * 注意：调用该方法时，请确保View已经测量完毕，如果宽高为0，则将抛出异常
     */
    public static Bitmap toBitmap(@NonNull View view) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        if (measuredWidth == 0 || measuredHeight == 0) {
            throw new RuntimeException("调用该方法时，请确保View已经测量完毕，如果宽高为0，则抛出异常以提醒！");
        }
        Bitmap screenshot = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(screenshot);
        Drawable background = view.getBackground();
        if (background != null) {
            background.setBounds(0, 0, view.getWidth(), measuredHeight);
            background.draw(canvas);
        } else {
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);// 将 view 画到画布上
        return screenshot;
    }

    /**
     * 获取指定类型的子View
     *
     * @param group viewGroup
     * @param cls   如：RecyclerView.class
     * @param <T>
     * @return 指定类型的View
     */
    public static <T> T findTypeView(@Nullable ViewGroup group, Class<T> cls) {
        if (group == null) {
            return null;
        }
        Deque<View> deque = new ArrayDeque<>();
        deque.add(group);
        while (!deque.isEmpty()) {
            View node = deque.removeFirst();
            if (cls.isInstance(node)) {
                return cls.cast(node);
            } else if (node instanceof ViewGroup) {
                ViewGroup container = (ViewGroup) node;
                for (int i = 0, count = container.getChildCount(); i < count; i++) {
                    deque.add(container.getChildAt(i));
                }
            }
        }
        return null;
    }

    public static boolean isActivityDestroyed(Context context) {
        Activity activity = findActivity(context);
        if (activity != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                return activity.isDestroyed() || activity.isFinishing();
            }
            return activity.isFinishing();
        }
        return true;
    }

    @Nullable
    public static Activity findActivity(Context context) {
        if (context instanceof Activity) return (Activity) context;
        else if (context instanceof ContextWrapper) {
            return findActivity(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }
}
