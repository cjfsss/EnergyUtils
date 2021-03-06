package hos.util.compat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;




import java.io.File;
import java.util.List;

import hos.util.listener.UriCallback;
import hos.util.utils.StringUtils;
import hos.util.wps.WpsParams;

/**
 * <p>Title: IntentUtils </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2021/7/12 13:45
 */
public class IntentCompat {

    /**
     * Return the name of launcher activity.
     *
     * @param pkg The name of the package.
     * @return the name of launcher activity
     */

    public static String getLauncherActivity( Context context,  final String pkg) {
        if (StringUtils.isSpace(pkg)) return "";
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setPackage(pkg);
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> info = pm.queryIntentActivities(intent, 0);
        if (info == null || info.size() == 0) {
            return "";
        }
        return info.get(0).activityInfo.name;
    }

    /**
     * Return the intent of launch app.
     *
     * @param pkgName The name of the package.
     * @return the intent of launch app
     */

    public static Intent getLaunchAppIntent( Context context, final String pkgName) {
        String launcherActivity = getLauncherActivity(context, pkgName);
        if (StringUtils.isSpace(launcherActivity)) return null;
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setClassName(pkgName, launcherActivity);
        return intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    /**
     * Return the intent of launch app details settings.
     *
     * @param pkgName The name of the package.
     * @return the intent of launch app details settings
     */

    public static Intent getLaunchAppDetailsSettingsIntent(final String pkgName) {
        return getLaunchAppDetailsSettingsIntent(pkgName, false);
    }

    /**
     * Return the intent of launch app details settings.
     *
     * @param pkgName The name of the package.
     * @return the intent of launch app details settings
     */

    public static Intent getLaunchAppDetailsSettingsIntent(final String pkgName, final boolean isNewTask) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + pkgName));
        return getIntent(intent, isNewTask);
    }


    private static Intent getIntent(final Intent intent, final boolean isNewTask) {
        return isNewTask ? intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) : intent;
    }


    public static Intent getLaunchTargetOpenFile( Context context,  final String pkgName,
                                                  final String filePath,  UriCallback callback) {
        if (StringUtils.toNULL(filePath) == null) {
            return null;
        }
        return getLaunchTargetOpenFile(context, pkgName, new File(filePath), callback);
    }


    public static Intent getLaunchTargetOpenFile( Context context,  final String pkgName,
                                                  final File file,  UriCallback callback) {
        if (file == null) {
            return null;
        }
        Intent intent = getLaunchAppIntent(context, pkgName);
        if (intent == null) {
            return null;
        }
        final Uri uri;
        if (Build.VERSION.SDK_INT >= 23) {
            // 加入读取权限 android 7.0以上时，URI不能直接暴露
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            uri = callback.getUriForFile(context, file);
        } else {
            uri = Uri.fromFile(file);
        }
        /* 依扩展名的类型决定MimeType */
        String type = FileCompat.getFileTypeString(file.getAbsolutePath());
        intent.setDataAndType(uri, type);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }


    public static Intent getLaunchOpenFile( Context context,  String filePath,  UriCallback callback) {
        if (StringUtils.toNULL(filePath) == null) {
            return null;
        }
        return getLaunchOpenFile(context, new File(filePath), callback);
    }


    public static Intent getLaunchOpenFile( Context context,  File file,  UriCallback callback) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String type = FileCompat.getFileTypeString(file.getAbsolutePath());
        final Uri uri;
        if (Build.VERSION.SDK_INT >= 23) {
            // 加入读取权限
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            uri = callback.getUriForFile(context, file);
        } else {
            uri = Uri.fromFile(file);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(uri, type);
        return intent;
    }


    public static Intent getLaunchOpenWPS( Context context,  String filePath,  UriCallback callback) {
        if (StringUtils.toNULL(filePath) == null) {
            return null;
        }
        return getLaunchOpenWPS(context, new File(filePath), callback);
    }


    public static Intent getLaunchOpenWPS( Context context,  File file,  UriCallback callback) {
        // wps存在,打开WPS
        Intent intent = IntentCompat.getLaunchTargetOpenFile(
                context, WpsParams.PackageName.NORMAL, file, callback);
        if (intent == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        //打开模式
        bundle.putString(WpsParams.OPEN_MODE, WpsParams.OpenMode.NORMAL); // 打开模式
        bundle.putBoolean(WpsParams.SEND_SAVE_BROAD, true);
        //关闭时是否发送广播
        bundle.putString(WpsParams.THIRD_PACKAGE, context.getPackageName());
        bundle.putBoolean(WpsParams.SEND_CLOSE_BROAD, true); // 关闭时是否发送广播
        //第三方应用的包名，用于对改应用合法性的验证
        bundle.putBoolean(WpsParams.CLEAR_TRACE, true);
        //清除打开记录
        //bundle.putBoolean(CLEAR_FILE, true);
        //关闭后删除打开文件
        intent.setAction(Intent.ACTION_VIEW);
        intent.setClassName(WpsParams.PackageName.NORMAL, WpsParams.ClassName.NORMAL);
        // bundle.putBoolean(CLEAR_FILE, true); //关闭后删除打开文件
        intent.putExtras(bundle);
        context.startActivity(intent);
        return intent;
    }

    /**
     * 打开应用市场意图
     *
     * @param packageName 要打开应用的包名
     * @return
     */

    public static Intent getLaunchMarket( String packageName) {
        return new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName));
    }

    /**
     * 获取打开网页浏览器
     *
     * @param url 访问地址
     * @return
     */

    public static Intent getLaunchUrl( String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        //这个目的是为了 防止在部分机型上面 拉不起浏览器，，比说华为
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        // 是为了 使用applicaiton  context 启动activity 不会报错
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }

}
