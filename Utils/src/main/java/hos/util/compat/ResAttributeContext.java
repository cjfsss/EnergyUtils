package hos.util.compat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.TypedValue;




import hos.util.R;


/**
 * <p>Title: ResAttributeUtils </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/1/2 9:16
 */
public class ResAttributeContext {

    /**
     * 获取主题中的属性
     *
     * @param resId 资源ID
     * @return
     */

    public static TypedValue getAttribute( Context context, int resId) {
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(resId, typedValue, true);
        return typedValue;
    }

    /**
     * 获取主题中的图标资源属性
     *
     * @param resId 资源ID
     * @return
     */
    public static int getAttributeRes( Context context, int resId) {
        try {
            return getAttribute(context, resId).resourceId;
        } catch (Exception e) {
            return 0;
        }
    }

//    public static int getColorPrimary( Context context) {
//        return getAttribute(context, R.attr.colorPrimary).data;
//    }

    /**
     * 获取主题中的字符串资源属性
     *
     * @param resId 资源ID
     * @return
     */

    public static String getAttributeString( Context context, int resId) {
        try {
            return String.valueOf(getAttribute(context, resId).coerceToString());
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 获取主题中的图标资源属性
     *
     * @param resId 资源ID
     * @return
     */

    public static Bitmap getAttributeBitmap( Context context, int resId) {
        try {
            return getBitmap(context, getAttributeRes(context, resId));
        } catch (Exception e) {
            return null;
        }
    }


    private static Bitmap getBitmap( Context context, int id) {
        return BitmapFactory.decodeResource(context.getResources(), id);
    }

}
