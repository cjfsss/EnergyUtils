package hos.util.compat;

import android.app.Application;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import hos.core.ActivityManager;
import hos.core.AppCompat;
import hos.util.carash.CrashHandler;
import hos.util.log.HiConsolePrinter;
import hos.util.log.HiLogConfig;
import hos.util.log.HiLogManager;
import hos.util.log.HiLogPrinter;

/**
 * <p>Title: AppCompatUtil </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2022/5/11 21:48
 */
public class AppCompatUtil {

    @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public static void init(@NonNull Application application, HiLogConfig.JsonParser jsonParser) {
        AppCompat.init(application);
        ActivityManager.init(application);
        CrashHandler.init();
        HiLogManager.init(new HiLogConfig() {
            @Override
            public JsonParser injectJsonParser() {
                return jsonParser;
            }
        }, new HiConsolePrinter());
    }
}
