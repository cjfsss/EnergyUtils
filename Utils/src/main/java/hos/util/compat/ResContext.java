package hos.util.compat;

import android.animation.AnimatorInflater;
import android.animation.StateListAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;


/**
 * <p>Title: ils </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/1/2 9:44
 */
public class ResContext {


    /**
     * 获取字符串
     */
    public static String getString(Context context,  int id) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getString(id);
        } else {
            return context.getResources().getString(id);
        }
    }

    /**
     * 获取字符串
     *
     * @return
     */
    public static String getString(Context context,  int id, Object... formatArgs) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getString(id, formatArgs);
        } else {
            return context.getResources().getString(id, formatArgs);
        }
    }

    public static float getTextSize(Context context,  int id) {
        return context.getResources().getDimension(id);
    }

    public static CharSequence[] getTextArray(Context context,  int id) {

        return context.getResources().getTextArray(id);
    }

    public static String[] getStringArray(Context context,  int id) {
        return context.getResources().getStringArray(id);
    }

    public static int[] getIntArray(Context context,  int id) {
        return context.getResources().getIntArray(id);
    }

    public static int[] getMipmapArray(Context context,  int id) {
        @SuppressLint("Recycle") TypedArray array = context.getResources().obtainTypedArray(id);
        int len = array.length();
        int[] oaIcon = new int[len];
        for (int i = 0; i < len; i++) {
            oaIcon[i] = array.getResourceId(i, 0);
        }
        array.recycle();
        return oaIcon;
    }

    
    public static Drawable getDrawable( Context context,  int id) {
        if (Build.VERSION.SDK_INT >= 21) {
            return context.getDrawable(id);
        }
        return context.getResources().getDrawable(id);
    }

    
    public static ColorStateList getColorStateList( Context context,  int id) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getColorStateList(id);
        } else {
            return context.getResources().getColorStateList(id);
        }
    }


    public static int getColor(Context context,  int id) {
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getColor(id);
        } else {
            return context.getResources().getColor(id);
        }
    }

    public static StateListAnimator getStateListAnimator(Context context,  int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return AnimatorInflater.loadStateListAnimator(context, id);
        }
        return null;
    }

    /**
     * 判断是否是平板
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isTablet( Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >=
                Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

}
