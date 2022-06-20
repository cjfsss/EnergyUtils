package hos.util.utils;

import android.app.Activity;
import android.os.Build;
import android.text.TextUtils;
import android.view.WindowManager;




import java.util.Locale;

/**
 * <p>Title: SafetyHelper </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2022/4/5 17:38
 */
public class SafetyHelper {
    /**
     * 判断是否是真机
     *
     * @return true 真机 false 模拟器
     */
    public static boolean isRealPhone() {

        String arch = System.getProperties().getProperty("os.arch");
        if (arch == null) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            arch = arch.toUpperCase(Locale.ROOT);
        } else {
            arch = arch.toUpperCase();
        }
        return TextUtils.equals(arch, "AARCH64") || TextUtils.equals(arch, "AARCH32");
    }

    /**
     * 设置当前Activity 不能被录屏和截图
     *
     * @param activity 当前需要禁止录屏和截图的页面
     */
    public static void removeScreen( Activity activity) {
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
    }

    /**
     * 设置当前Activity 能被录屏和截图
     *
     * @param activity 当前需要录屏和截图的页面
     */
    public static void openScreen( Activity activity) {
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_SECURE);
    }

    /**
     * 设置当前Activity 一直亮着
     *
     * @param activity 当前需要一直亮着的页面
     */
    public static void openBright( Activity activity) {
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    /**
     * 设置当前Activity 不一直亮着
     *
     * @param activity 当前需要不一直亮着的页面
     */
    public static void removeBright( Activity activity) {
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
}
