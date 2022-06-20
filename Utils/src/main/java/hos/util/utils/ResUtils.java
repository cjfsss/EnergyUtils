package hos.util.utils;

import android.animation.StateListAnimator;
import android.app.Application;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Build;

import hos.core.AppCompat;
import hos.util.compat.ResContext;


/**
 * <p>Title: ResUtils </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/1/2 9:44
 */
public class ResUtils {

    private static Application getApplication() {
        return AppCompat.getApp();
    }


//    /**
//     * 获取colorPrimary的颜色,需要V7包的支持
//     *
//     * @return 0xAARRGGBB
//     */
//    public static int getColorPrimary() {
//        return ResAttributeContext.getColorPrimary(getApplication());
//    }


    /**
     * 获取字符串
     */
    public static String getString(int id) {
        return ResContext.getString(getApplication(), id);
    }

    /**
     * 获取字符串
     *
     * @return
     */
    public static String getString(int id, Object... formatArgs) {
        return ResContext.getString(getApplication(), id, formatArgs);
    }

    public static CharSequence[] getTextArray(int id) {
        return ResContext.getTextArray(getApplication(), id);
    }

    public static String[] getStringArray(int id) {
        return ResContext.getStringArray(getApplication(), id);
    }

    public static int[] getIntArray(int id) {
        return ResContext.getIntArray(getApplication(), id);
    }

    public static int[] getMipmapArray(int id) {
        return ResContext.getMipmapArray(getApplication(), id);
    }


    public static Drawable getDrawable(int id) {
        return ResContext.getDrawable(getApplication(), id);
    }


    public static ColorStateList getColorStateList(int id) {
        return ResContext.getColorStateList(getApplication(), id);
    }


    public static int getColor(int id) {
        return ResContext.getColor(getApplication(), id);
    }

    public static StateListAnimator getStateListAnimator(int id) {
        return ResContext.getStateListAnimator(getApplication(), id);
    }

    /**
     * 判断是否是平板
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isTablet() {
        return (getApplication().getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >=
                Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

}
