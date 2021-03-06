package hos.util.path;

import android.app.Application;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;




import java.io.File;

import hos.core.AppCompat;


/**
 * <p>Title: PathManager </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2021/2/20 9:12
 */
public class PathManager {

    private static Application getApplication() {
        return AppCompat.getApp();
    }

    //<editor-fold desc="外部储存">
    /**
     * 获取外部储存
     */
    
    public static String getExternal() {
        try {
            if (SDCardUtils.isSDCardEnableByEnvironment()
                    && !TextUtils.isEmpty(SDCardUtils.getSDCardPathByEnvironment())) {
                // 外部存储可以使用
                String environment = SDCardUtils.getSDCardPathByEnvironment();
                if (environment == null || TextUtils.isEmpty(environment)) {
                    // /storage/emulated/0
                    // 没有外部储存
                    return getExternalAndroid();
                }
                return environment;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getExternalAndroid();
    }

    /**
     * 获取外部储存
     */
    
    public static String getExternal( String type) {
        return getExternal() + File.separator + type;
    }
    //</editor-fold>

    //<editor-fold desc="内部存储">
    /**
     * 获取内部存储
     */
    
    public static String getInterior() {
        // /data/data/com.learn.test
        String path = getApplication().getFilesDir().getAbsolutePath();
        return path.substring(0, path.lastIndexOf("/"));
    }

    /**
     * 获取内部存储
     */
    
    public static String getInterior( String type) {
        // /data/data/com.learn.test
        return getInterior() + File.separator + type;
    }

    /**
     * 获取内部文件存储
     */
    
    public static String getInteriorFile() {
        // /data/data/com.learn.test/files
        return getApplication().getFilesDir().getAbsolutePath();
    }

    /**
     * 获取内部文件存储
     */
    
    public static String getInteriorFile( String type) {
        // /data/data/com.learn.test/files
        return getInteriorFile() + File.separator + type;
    }

    /**
     * 获取内部缓存存储
     */
    
    public static String getInteriorCache() {
        // /data/data/com.learn.test/cache
        return getApplication().getCacheDir().getAbsolutePath();
    }

    /**
     * 获取内部缓存存储
     */
    
    public static String getInteriorCache( String type) {
        // /data/data/com.learn.test/cache
        return getInteriorCache() + File.separator + type;
    }

    /**
     * 获取内部下载存储
     */
    
    public static String getInteriorDownload() {
        // /data/data/com.learn.test/Download
        return getInterior(Environment.DIRECTORY_DOWNLOADS);
    }

    /**
     * 获取内部下载存储
     */
    
    public static String getInteriorDownload( String type) {
        // /data/data/com.learn.test/Download
        return getInteriorDownload() + File.separator + type;
    }

    /**
     * 获取内部视频存储
     */
    
    public static String getInteriorMovies() {
        // /data/data/com.learn.test/Movies
        return getInterior(Environment.DIRECTORY_MOVIES);
    }

    /**
     * 获取内部视频存储
     */
    
    public static String getInteriorMovies( String type) {
        // /data/data/com.learn.test/Movies
        return getInteriorMovies() + File.separator + type;
    }

    /**
     * 获取内部视频存储
     */
    
    public static String getInteriorAlarms() {
        // /data/data/com.learn.test/Alarms
        return getInterior(Environment.DIRECTORY_ALARMS);
    }

    /**
     * 获取内部视频存储
     */
    
    public static String getInteriorAlarms( String type) {
        // /data/data/com.learn.test/Alarms
        return getInteriorAlarms() + File.separator + type;
    }

    /**
     * 获取内部音乐存储
     */
    
    public static String getInteriorMusic() {
        // /data/data/com.learn.test/Music
        return getInterior(Environment.DIRECTORY_MUSIC);
    }

    /**
     * 获取内部音乐存储
     */
    
    public static String getInteriorMusic( String type) {
        // /data/data/com.learn.test/Music
        return getInteriorMusic() + File.separator + type;
    }

    /**
     * 获取内部铃声存储
     */
    
    public static String getInteriorRingtones() {
        // /data/data/com.learn.test/Ringtones
        return getInterior(Environment.DIRECTORY_RINGTONES);
    }

    /**
     * 获取内部音乐存储
     */
    
    public static String getInteriorRingtones( String type) {
        // /data/data/com.learn.test/Ringtones
        return getInteriorRingtones() + File.separator + type;
    }

    /**
     * 获取内部音乐存储
     */
    
    public static String getInteriorPictures() {
        // /data/data/com.learn.test/Pictures
        return getInterior(Environment.DIRECTORY_PICTURES);
    }

    /**
     * 获取内部音乐存储
     */
    
    public static String getInteriorPictures( String type) {
        // /data/data/com.learn.test/Pictures
        return getInteriorPictures() + File.separator + type;
    }

    /**
     * 获取内部音乐存储
     */
    
    public static String getInteriorDCIM() {
        // /data/data/com.learn.test/DCIM
        return getInterior(Environment.DIRECTORY_DCIM);
    }

    /**
     * 获取内部音乐存储
     */
    
    public static String getInteriorDCIM( String type) {
        // /data/data/com.learn.test/DCIM
        return getInteriorDCIM() + File.separator + type;
    }

    /**
     * 获取内部文档存储
     */
    
    public static String getInteriorDocuments() {
        // /data/data/com.learn.test/Documents
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return getInterior(Environment.DIRECTORY_DOCUMENTS);
        }
        return getInterior("Documents");
    }

    /**
     * 获取内部文档存储
     */
    
    public static String getInteriorDocuments( String type) {
        // /data/data/com.learn.test/Documents
        return getInteriorDocuments() + File.separator + type;
    }

    /**
     * 获取内部截图存储
     */
    
    public static String getInteriorScreenshots() {
        // /data/data/com.learn.test/Screenshots
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return getInterior(Environment.DIRECTORY_SCREENSHOTS);
        }
        return getInterior("Screenshots");
    }

    /**
     * 获取内部截图存储
     */
    
    public static String getInteriorScreenshots( String type) {
        // /data/data/com.learn.test/Screenshots
        return getInteriorScreenshots() + File.separator + type;
    }

    /**
     * 获取内部有声读物存储
     */
    
    public static String getInteriorAudiobooks() {
        // /data/data/com.learn.test/Audiobooks
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return getInterior(Environment.DIRECTORY_AUDIOBOOKS);
        }
        return getInterior("Audiobooks");
    }

    /**
     * 获取内部有声读物存储
     */
    
    public static String getInteriorAudiobooks( String type) {
        // /data/data/com.learn.test/Audiobooks
        return getInteriorAudiobooks() + File.separator + type;
    }

    /**
     * 获取内部log存储
     */
    
    public static String getInteriorLog() {
        // /data/data/com.learn.test/log
        return getInterior("log");
    }

    /**
     * 获取内部log存储
     */
    
    public static String getInteriorLog( String type) {
        // /data/data/com.learn.test/log
        return getInteriorLog() + File.separator + type;
    }

    /**
     * 获取内部压缩存储
     */
    
    public static String getInteriorZip() {
        // /data/data/com.learn.test/zip
        return getInterior("zip");
    }

    /**
     * 获取内部压缩存储
     */
    
    public static String getInteriorZip( String type) {
        // /data/data/com.learn.test/zip
        return getInteriorZip() + File.separator + type;
    }

    /**
     * 获取内部地图存储
     */
    
    public static String getInteriorMap() {
        // /data/data/com.learn.test/map
        return getInterior("map");
    }

    /**
     * 获取内部压缩存储
     */
    
    public static String getInteriorMap( String type) {
        // /data/data/com.learn.test/map
        return getInteriorMap() + File.separator + type;
    }

    /**
     * 获取内部APK存储
     */
    
    public static String getInteriorApk() {
        // /data/data/com.learn.test/apk
        return getInterior("apk");
    }

    /**
     * 获取内部压缩存储
     */
    
    public static String getInteriorApk( String type) {
        // /data/data/com.learn.test/apk
        return getInteriorApk() + File.separator + type;
    }

    /**
     * 获取内部压缩存储
     */
    
    public static String getDatabasePath( String name) {
        // /data/data/com.learn.test/database/{name}
        String databasePath = getApplication().getDatabasePath(name).getAbsolutePath();
        if (databasePath.contains(".db")) {
            return databasePath;
        }
        return databasePath + ".db";
    }
    //</editor-fold>

    //<editor-fold desc="标准外部储存">
    /**
     * 获取外部标准存储
     */
    
    public static String getExternalAndroid() {
        // /storage/emulated/0/Android/data/com.learn.test
        
        File file = null;
        try {
            file = getApplication().getExternalCacheDir();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (file == null) {
            // 没有外部储存的话
            return getInterior();
        }
        String path = file.getAbsolutePath();
        return path.substring(0, path.lastIndexOf("/"));
    }

    /**
     * 获取外部标准存储
     */
    
    public static String getExternalAndroid( String type) {
        // /storage/emulated/0/Android/data/com.learn.test
        return getExternalAndroid() + File.separator + type;
    }

    /**
     * 获取内部文件存储
     */
    
    public static String getExternalAndroidFile() {
        // /storage/emulated/0/Android/data/com.learn.test/files
        return getExternalAndroid("files");
    }

    /**
     * 获取内部文件存储
     */
    
    public static String getExternalAndroidFile( String type) {
        // /storage/emulated/0/Android/data/com.learn.test/files
        return getExternalAndroidFile() + File.separator + type;
    }

    /**
     * 获取内部缓存存储
     */
    
    public static String getExternalAndroidCache() {
        
        File file = getApplication().getExternalCacheDir();
        if (file == null) {
            // 没有外部储存的话
            return getInteriorCache();
        }
        // /storage/emulated/0/Android/data/com.learn.test/cache
        return file.getAbsolutePath();
    }

    /**
     * 获取内部缓存存储
     */
    
    public static String getExternalAndroidCache( String type) {
        // /storage/emulated/0/Android/data/com.learn.test/cache
        return getInteriorCache() + File.separator + type;
    }

    /**
     * 获取外部下载存储
     */
    
    public static String getExternalAndroidDownload() {
        // /storage/emulated/0/Android/data/com.learn.test/Download
        return getExternalAndroid(Environment.DIRECTORY_DOWNLOADS);
    }

    /**
     * 获取外部下载存储
     */
    
    public static String getExternalAndroidDownload( String type) {
        // /storage/emulated/0/Android/data/com.learn.test/Download
        return getExternalAndroidDownload() + File.separator + type;
    }

    /**
     * 获取外部视频存储
     */
    
    public static String getExternalAndroidMovies() {
        // /storage/emulated/0/Android/data/com.learn.test/Movies
        return getExternalAndroid(Environment.DIRECTORY_MOVIES);
    }

    /**
     * 获取外部视频存储
     */
    
    public static String getExternalAndroidMovies( String type) {
        // /storage/emulated/0/Android/data/com.learn.test/Movies
        return getExternalAndroidMovies() + File.separator + type;
    }

    /**
     * 获取外部视频存储
     */
    
    public static String getExternalAndroidAlarms() {
        // /storage/emulated/0/Android/data/com.learn.test/Alarms
        return getExternalAndroid(Environment.DIRECTORY_ALARMS);
    }

    /**
     * 获取外部视频存储
     */
    
    public static String getExternalAndroidAlarms( String type) {
        // /storage/emulated/0/Android/data/com.learn.test/Alarms
        return getExternalAndroidAlarms() + File.separator + type;
    }

    /**
     * 获取外部音乐存储
     */
    
    public static String getExternalAndroidMusic() {
        // /storage/emulated/0/Android/data/com.learn.test/Music
        return getExternalAndroid(Environment.DIRECTORY_MUSIC);
    }

    /**
     * 获取外部音乐存储
     */
    
    public static String getExternalAndroidMusic( String type) {
        // /storage/emulated/0/Android/data/com.learn.test/Music
        return getExternalAndroidMusic() + File.separator + type;
    }

    /**
     * 获取外部铃声存储
     */
    
    public static String getExternalAndroidRingtones() {
        // /storage/emulated/0/Android/data/com.learn.test/Ringtones
        return getExternalAndroid(Environment.DIRECTORY_RINGTONES);
    }

    /**
     * 获取外部音乐存储
     */
    
    public static String getExternalAndroidRingtones( String type) {
        // /storage/emulated/0/Android/data/com.learn.test/Ringtones
        return getExternalAndroidRingtones() + File.separator + type;
    }

    /**
     * 获取外部音乐存储
     */
    
    public static String getExternalAndroidPictures() {
        // /storage/emulated/0/Android/data/com.learn.test/Pictures
        return getExternalAndroid(Environment.DIRECTORY_PICTURES);
    }

    /**
     * 获取外部音乐存储
     */
    
    public static String getExternalAndroidPictures( String type) {
        // /storage/emulated/0/Android/data/com.learn.test/Pictures
        return getExternalAndroidPictures() + File.separator + type;
    }

    /**
     * 获取外部音乐存储
     */
    
    public static String getExternalAndroidDCIM() {
        // /storage/emulated/0/Android/data/com.learn.test/DCIM
        return getExternalAndroid(Environment.DIRECTORY_DCIM);
    }

    /**
     * 获取外部音乐存储
     */
    
    public static String getExternalAndroidDCIM( String type) {
        // /storage/emulated/0/Android/data/com.learn.test/DCIM
        return getExternalAndroidDCIM() + File.separator + type;
    }

    /**
     * 获取外部文档存储
     */
    
    public static String getExternalAndroidDocuments() {
        // /storage/emulated/0/Android/data/com.learn.test/Documents
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return getExternalAndroid(Environment.DIRECTORY_DOCUMENTS);
        }
        return getExternalAndroid("Documents");
    }

    /**
     * 获取外部文档存储
     */
    
    public static String getExternalAndroidDocuments( String type) {
        // /storage/emulated/0/Android/data/com.learn.test/Documents
        return getExternalAndroidDocuments() + File.separator + type;
    }

    /**
     * 获取外部截图存储
     */
    
    public static String getExternalAndroidScreenshots() {
        // /storage/emulated/0/Android/data/com.learn.test/Screenshots
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return getExternalAndroid(Environment.DIRECTORY_SCREENSHOTS);
        }
        return getExternalAndroid("Screenshots");
    }

    /**
     * 获取外部截图存储
     */
    
    public static String getExternalAndroidScreenshots( String type) {
        // /storage/emulated/0/Android/data/com.learn.test/Screenshots
        return getExternalAndroidScreenshots() + File.separator + type;
    }

    /**
     * 获取外部有声读物存储
     */
    
    public static String getExternalAndroidAudiobooks() {
        // /storage/emulated/0/Android/data/com.learn.test/Audiobooks
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return getExternalAndroid(Environment.DIRECTORY_AUDIOBOOKS);
        }
        return getExternalAndroid("Audiobooks");
    }

    /**
     * 获取外部有声读物存储
     */
    
    public static String getExternalAndroidAudiobooks( String type) {
        // /storage/emulated/0/Android/data/com.learn.test/Audiobooks
        return getExternalAndroidAudiobooks() + File.separator + type;
    }

    /**
     * 获取外部log存储
     */
    
    public static String getExternalAndroidLog() {
        // /storage/emulated/0/Android/data/com.learn.test/log
        return getExternalAndroid("log");
    }

    /**
     * 获取外部log存储
     */
    
    public static String getExternalAndroidLog( String type) {
        // /storage/emulated/0/Android/data/com.learn.test/log
        return getExternalAndroidLog() + File.separator + type;
    }

    /**
     * 获取外部压缩存储
     */
    
    public static String getExternalAndroidZip() {
        // /storage/emulated/0/Android/data/com.learn.test/zip
        return getExternalAndroid("zip");
    }

    /**
     * 获取外部压缩存储
     */
    
    public static String getExternalAndroidZip( String type) {
        // /storage/emulated/0/Android/data/com.learn.test/zip
        return getExternalAndroidZip() + File.separator + type;
    }

    /**
     * 获取外部地图存储
     */
    
    public static String getExternalAndroidMap() {
        // /storage/emulated/0/Android/data/com.learn.test/map
        return getExternalAndroid("map");
    }

    /**
     * 获取外部压缩存储
     */
    
    public static String getExternalAndroidMap( String type) {
        // /storage/emulated/0/Android/data/com.learn.test/map
        return getExternalAndroidMap() + File.separator + type;
    }

    /**
     * 获取外部APK存储
     */
    
    public static String getExternalAndroidApk() {
        // /storage/emulated/0/Android/data/com.learn.test/apk
        return getExternalAndroid("apk");
    }

    /**
     * 获取外部压缩存储
     */
    
    public static String getExternalAndroidApk( String type) {
        // /storage/emulated/0/Android/data/com.learn.test/apk
        return getExternalAndroidApk() + File.separator + type;
    }

    /**
     * 获取外部压缩存储
     */
    
    public static String getExternalAndroidDatabasePath( String name) {
        // /storage/emulated/0/Android/data/com.learn.test/database/{name}
        
        File file = getApplication().getExternalCacheDir();
        if (file == null) {
            // 没有外部储存的话
            return getDatabasePath(name);
        }
        String path = file.getAbsolutePath();
        String rootPath = path.substring(0, path.lastIndexOf("/"));
        String databasePath = new File(rootPath + "/database/" + name).getAbsolutePath();
        if (databasePath.contains(".db")) {
            return databasePath;
        }
        return databasePath + ".db";
    }
    //</editor-fold>
}
