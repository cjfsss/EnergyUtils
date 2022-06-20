package hos.util.carash;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;



import java.io.File;

import hos.core.AppCompat;

/**
 * <p>Title: CrashHandler </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2022/3/30 23:37
 */
public class CrashHandler {

    public static void init( String crashDir,  String prefix) {
        Thread.setDefaultUncaughtExceptionHandler(new CaughtExceptionHandler(AppCompat.getApp(), crashDir, prefix));
    }

    public static void init( String prefix) {
        Thread.setDefaultUncaughtExceptionHandler(new CaughtExceptionHandler(AppCompat.getApp(), prefix));
    }

    public static void init() {
        Thread.setDefaultUncaughtExceptionHandler(new CaughtExceptionHandler(AppCompat.getApp()));
    }

    public static boolean delete() {
        try {
            Application app = AppCompat.getApp();
            SharedPreferences log = app.getSharedPreferences("log", Context.MODE_PRIVATE);
            String logDir = log.getString("log", app.getCacheDir().getAbsolutePath() + File.separator + "log");
            File file = new File(logDir);
            if (file.exists()) {
                return file.delete();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
