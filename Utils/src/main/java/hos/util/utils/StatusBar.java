package hos.util.utils;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import hos.core.AppCompat;

/**
 * <p>Title: StatusBar </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2022/4/3 12:36
 */
public class StatusBar {


    /**
     * darkContent true:意味着 白底黑字， false:黑底白字
     * <p>
     * statusBarColor  状态栏的背景色
     * <p>
     * translucent  沉浸式效果，也就是页面的布局延伸到状态栏之下
     */
    public static void setStatusBar(
            Activity activity,
            boolean darkContent,
            int statusBarColor
    ) {
        setStatusBar(activity, darkContent, statusBarColor, false);
    }

    /**
     * darkContent true:意味着 白底黑字， false:黑底白字
     * <p>
     * statusBarColor  状态栏的背景色
     * <p>
     * translucent  沉浸式效果，也就是页面的布局延伸到状态栏之下
     */
    public static void setStatusBar(
            Activity activity,
            boolean darkContent,
            int statusBarColor,
            boolean translucent
    ) {
        Window window = activity.getWindow();
        View decorView = window.getDecorView();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //请求系统 绘制状态栏的背景色
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //这俩不能同时出现
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(statusBarColor);
        }

        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
            return;
        }
        int visibility = decorView.getSystemUiVisibility();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (darkContent) {
                //白底黑字--浅色主题
                visibility = visibility | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            } else {
                //黑底白字--深色主题
                // java  visibility &= ~ View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                visibility &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
        }

        if (translucent) {
            //此时 能够使得页面的布局延伸到状态栏之下，但是状图兰的图标 也看不见了-,使得状图兰的图标 恢复可见性
            //bugfix:手快写错了 应该是 SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN。
            visibility =
                    visibility | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        }

        decorView.setSystemUiVisibility(visibility);
    }

    /**
     * Return the status bar's height.
     *
     * @return the status bar's height
     */
    public static int getStatusBarHeight() {
        Resources resources = AppCompat.getApp().getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }


    /**
     * Return the navigation bar's height.
     *
     * @return the navigation bar's height
     */
    public static int getNavBarHeight() {
        Resources res = AppCompat.getApp().getResources();
        int resourceId = res.getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId != 0) {
            return res.getDimensionPixelSize(resourceId);
        } else {
            return 0;
        }
    }
}
