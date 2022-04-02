package hos.util.carash;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import hos.core.ActivityManager;
import hos.util.BuildConfig;
import hos.util.log.HiLog;

/**
 * <p>Title: CaughtExceptionHandler </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2022/3/30 23:37
 */
class CaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    @NonNull
    private final Context context;
    @NonNull
    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.CHINA);
    @NonNull
    private final String LAUNCH_TIME = formatter.format(new Date());
    private final Thread.UncaughtExceptionHandler defaultExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
    @NonNull
    private final String crashDir;
    @NonNull
    private final String prefix;

    CaughtExceptionHandler(@NonNull Context context, @NonNull String crashDir, @NonNull String prefix) {
        this.context = context;
        this.crashDir = crashDir;
        this.prefix = prefix;
        SharedPreferences log = context.getSharedPreferences("log", Context.MODE_PRIVATE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            log.edit().putString("log", crashDir).apply();
        } else {
            log.edit().putString("log", crashDir).commit();
        }
    }

    CaughtExceptionHandler(@NonNull Context context, @NonNull String prefix) {
        this(context, context.getCacheDir().getAbsolutePath() + File.separator + "log", prefix);
    }

    CaughtExceptionHandler(@NonNull Context context) {
        this(context, "e");
    }

    @Override
    public void uncaughtException(@NonNull Thread t, @NonNull Throwable e) {
        if (!handleException(e) && defaultExceptionHandler != null) {
            defaultExceptionHandler.uncaughtException(t, e);
        }
        restartApp();
    }

    private void restartApp() {
        Intent intent =
                context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);

        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    private boolean handleException(@Nullable Throwable e) {
        if (e == null) return false;
        String log = collectDeviceInfo(e);
        if (BuildConfig.DEBUG) {
            HiLog.e(log);
        }
        saveCrashInfo2File(log);
        return true;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void saveCrashInfo2File(@NonNull String log) {
        File crashDir = new File(this.crashDir);
        if (!crashDir.exists()) {
            crashDir.mkdirs();
        }
        File crashFile = new File(crashDir, this.prefix + "_log" + formatter.format(new Date()) + "-crash.log");
        FileOutputStream fos = null;
        try {
            crashFile.createNewFile();
            fos = new FileOutputStream(crashFile);
            fos.write(log.getBytes());
            fos.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /**
     * 设备类型、OS本版、线程名、前后台、使用时长、App版本、升级渠道
     * CPU架构、内存信息、存储信息、permission权限
     */
    @NonNull
    private String collectDeviceInfo(@NonNull Throwable e) {
        StringBuilder sb = new StringBuilder();
        sb.append("brand=").append(Build.BRAND).append("\n");// huawei,xiaomi
        sb.append("rom=").append(Build.MODEL).append("\n"); //sm-G9550
        sb.append("os=").append(Build.VERSION.RELEASE).append("\n");//9.0
        sb.append("sdk=").append(Build.VERSION.SDK_INT).append("\n");//28
        sb.append("launch_time=").append(LAUNCH_TIME).append("\n");//启动APP的时间
        sb.append("crash_time=").append(formatter.format(new Date())).append("\n");//crash发生的时间
        sb.append("forground=").append(!ActivityManager.getInstance().isBackground()).append("\n");//应用处于前后台
        sb.append("thread=").append(Thread.currentThread().getName()).append("\n");//异常线程名
        sb.append("cpu_arch=").append(Build.CPU_ABI).append("\n");//armv7 armv8

        //app 信息
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            sb.append("version_code=").append(packageInfo.versionCode).append("\n");
            sb.append("version_name=").append(packageInfo.versionName).append("\n");
            sb.append("package_name=").append(packageInfo.packageName).append("\n");
            sb.append("requested_permission=").append(Arrays.toString(packageInfo.requestedPermissions)).append("\n");//已申请到那些权限
        } catch (PackageManager.NameNotFoundException nameNotFoundException) {
            nameNotFoundException.printStackTrace();
        }


        try {
            //统计一波 存储空间的信息，
            android.app.ActivityManager.MemoryInfo memInfo = new android.app.ActivityManager.MemoryInfo();
            android.app.ActivityManager ams =
                    (android.app.ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            ams.getMemoryInfo(memInfo);
            sb.append("availMem=").append(Formatter.formatFileSize(context, memInfo.availMem)).append("\n");//可用内存
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                sb.append("totalMem=").append(Formatter.formatFileSize(context, memInfo.totalMem)).append("\n");//设备总内存
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        try {
            File file = Environment.getExternalStorageDirectory();
            StatFs statFs = new StatFs(file.getPath());
            long availableSize;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                availableSize = statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
            } else {
                availableSize = statFs.getAvailableBlocks() * statFs.getBlockSize();
            }
            sb.append("availStorage=").append(Formatter.formatFileSize(context, availableSize)).append("\n");//存储空间
        } catch (Exception exception) {
            exception.printStackTrace();
        }

        Writer write = new StringWriter();
        PrintWriter printWriter = new PrintWriter(write);
        e.printStackTrace(printWriter);
        Throwable cause = e.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }

        printWriter.close();
        sb.append(write.toString());
        return sb.toString();
    }
}
