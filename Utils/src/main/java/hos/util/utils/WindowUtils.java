package hos.util.utils;

import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.StyleRes;

/**
 * <p>Title: WindowUtils </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2022/4/25 21:37
 */
public class WindowUtils {

    /**
     * 设置宽度为屏宽、位置靠近屏幕底部
     *
     * @param window 框口
     */
    public static void applyBottom(Window window) {
        applyBottom(window, 0);
    }

    /**
     * 设置宽度为屏宽、位置靠近屏幕底部
     *
     * @param window 框口
     */
    public static void applyBottom(Window window, @StyleRes int resId) {
        // 设置宽度为屏宽、位置靠近屏幕底部
        window.setBackgroundDrawableResource(android.R.color.white);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        // 设置动画
        if (resId != 0) {
            window.setWindowAnimations(resId);
        }
        window.setAttributes(wlp);
    }

    /**
     * 设置宽度为屏宽、位置靠近屏幕顶部
     *
     * @param window 框口
     */
    public static void applyTop(Window window) {
        applyTop(window, 0);
    }

    /**
     * 设置宽度为屏宽、位置靠近屏幕顶部
     *
     * @param window 框口
     */
    public static void applyTop(Window window, @StyleRes int resId) {
        // 设置宽度为屏宽、位置靠近屏幕底部
        window.setBackgroundDrawableResource(android.R.color.white);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.TOP;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        // 设置动画
        if (resId != 0) {
            window.setWindowAnimations(resId);
        }
        window.setAttributes(wlp);
    }

    /**
     * 设置宽度为屏宽、位置靠近屏幕右部
     *
     * @param window 框口
     */
    public static void applyRight(Window window) {
        applyRight(window, 0, 0);
    }

    /**
     * 设置宽度为屏宽、位置靠近屏幕右部
     *
     * @param window 框口
     */
    public static void applyRight(Window window, int width) {
        applyRight(window, 0, width);
    }

    /**
     * 设置宽度为屏宽、位置靠近屏幕右部
     *
     * @param window 框口
     */
    public static void applyRight(Window window, @StyleRes int resId, int width) {
        // 设置宽度为屏宽、位置靠近屏幕底部
        window.setBackgroundDrawableResource(android.R.color.white);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.RIGHT;
        if (width == 0) {
            wlp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        } else {
            wlp.width = width;
        }
        wlp.height = WindowManager.LayoutParams.MATCH_PARENT;
        // 设置动画
        if (resId != 0) {
            window.setWindowAnimations(resId);
        }
        window.setAttributes(wlp);
    }

    /**
     * @param window 框口
     */
    public static void applyFull(Window window) {
        applyFull(window, 0);
    }

    /**
     * @param window 框口
     */
    public static void applyFull(Window window, @StyleRes int resId) {
        // 设置宽度为屏宽、位置靠近屏幕底部
        window.setBackgroundDrawableResource(android.R.color.white);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.height = WindowManager.LayoutParams.MATCH_PARENT;
        // 设置动画
        if (resId != 0) {
            window.setWindowAnimations(resId);
        }
        window.setAttributes(wlp);
    }
}
