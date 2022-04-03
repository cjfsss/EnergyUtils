package hos.util.cache;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import hos.core.AppCompat;
import hos.util.listener.OnProgressUpdateListener;

/**
 * <p>Title: CacheUtils </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2022/4/2 20:54
 */
public class CacheUtils {

    static boolean clearCache() {
        File cacheDir = AppCompat.getApp().getCacheDir();
        return deleteAllInDir(cacheDir.getAbsolutePath() + File.separator + "cache");
    }

    static File getCacheFile(Cache cache) {
        return getCacheFile(cache.getKey());
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
     static File getCacheFile(String key) {
        File cacheDir = AppCompat.getApp().getCacheDir();
        File cacheFile = new File(cacheDir.getAbsolutePath() + File.separator + "cache", key + ".cache");
        if (!isFileExists(cacheFile)) {
            try {
                cacheFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return cacheFile;
    }

    /**
     * Return whether the file exists.
     *
     * @param file The file.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isFileExists(final File file) {
        if (file == null) return false;
        if (file.exists()) {
            return true;
        }
        return isFileExists(file.getAbsolutePath());
    }

    /**
     * Return whether the file exists.
     *
     * @param filePath The path of file.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isFileExists(final String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return false;
        }
        File file = new File(filePath);
        if (file.exists()) {
            return true;
        }
        return isFileExistsApi29(filePath);
    }

    private static boolean isFileExistsApi29(String filePath) {
        if (Build.VERSION.SDK_INT >= 29) {
            try {
                Uri uri = Uri.parse(filePath);
                ContentResolver cr = AppCompat.getApp().getContentResolver();
                AssetFileDescriptor afd = cr.openAssetFileDescriptor(uri, "r");
                if (afd == null) return false;
                try {
                    afd.close();
                } catch (IOException ignore) {
                }
            } catch (FileNotFoundException e) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Create a file if it doesn't exist, otherwise do nothing.
     *
     * @param file The file.
     * @return {@code true}: exists or creates successfully<br>{@code false}: otherwise
     */
    public static boolean createOrExistsFile(final File file) {
        if (file == null) return false;
        if (file.exists()) return file.isFile();
        if (!createOrExistsDir(file.getParentFile())) return false;
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Create a directory if it doesn't exist, otherwise do nothing.
     *
     * @param file The file.
     * @return {@code true}: exists or creates successfully<br>{@code false}: otherwise
     */
    public static boolean createOrExistsDir(final File file) {
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }


    /**
     * Return the bytes in file by stream.
     *
     * @param file The file.
     * @return the bytes in file
     */
    public static byte[] readFile2BytesByStream(final File file) {
        return readFile2BytesByStream(file, null);
    }

    /**
     * Return the bytes in file by stream.
     *
     * @param file     The file.
     * @param listener The progress update listener.
     * @return the bytes in file
     */
    public static byte[] readFile2BytesByStream(final File file,
                                                final OnProgressUpdateListener listener) {
        if (!isFileExists(file)) return null;
        try {
            ByteArrayOutputStream os = null;
            InputStream is = new BufferedInputStream(new FileInputStream(file), 524288);
            try {
                os = new ByteArrayOutputStream();
                byte[] b = new byte[524288];
                int len;
                if (listener == null) {
                    while ((len = is.read(b, 0, 524288)) != -1) {
                        os.write(b, 0, len);
                    }
                } else {
                    double totalSize = is.available();
                    int curSize = 0;
                    listener.onProgressUpdate(0, curSize, totalSize);
                    while ((len = is.read(b, 0, 524288)) != -1) {
                        os.write(b, 0, len);
                        curSize += len;
                        listener.onProgressUpdate(curSize / totalSize, curSize, totalSize);
                    }
                }
                return os.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (os != null) {
                        os.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Delete the file.
     *
     * @param file The file.
     * @return {@code true}: success<br>{@code false}: fail
     */
    public static boolean deleteFile(final File file) {
        return file != null && (!file.exists() || file.isFile() && file.delete());
    }

    /**
     * Delete the all in directory.
     *
     * @param dirPath The path of directory.
     * @return {@code true}: success<br>{@code false}: fail
     */
    public static boolean deleteAllInDir(final String dirPath) {
        return deleteAllInDir(new File(dirPath));
    }

    /**
     * Delete the all in directory.
     *
     * @param dir The directory.
     * @return {@code true}: success<br>{@code false}: fail
     */
    public static boolean deleteAllInDir(final File dir) {
        return deleteFilesInDirWithFilter(dir, new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return true;
            }
        });
    }


    /**
     * Delete all files that satisfy the filter in directory.
     *
     * @param dir    The directory.
     * @param filter The filter.
     * @return {@code true}: success<br>{@code false}: fail
     */
    public static boolean deleteFilesInDirWithFilter(final File dir, final FileFilter filter) {
        if (dir == null || filter == null) return false;
        // dir doesn't exist then return true
        if (!dir.exists()) return true;
        // dir isn't a directory then return false
        if (!dir.isDirectory()) return false;
        File[] files = dir.listFiles();
        if (files != null && files.length != 0) {
            for (File file : files) {
                if (filter.accept(file)) {
                    if (file.isFile()) {
                        if (!file.delete()) return false;
                    } else if (file.isDirectory()) {
                        if (!deleteDir(file)) return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Delete the directory.
     *
     * @param dir The directory.
     * @return {@code true}: success<br>{@code false}: fail
     */
    private static boolean deleteDir(final File dir) {
        if (dir == null) return false;
        // dir doesn't exist then return true
        if (!dir.exists()) return true;
        // dir isn't a directory then return false
        if (!dir.isDirectory()) return false;
        File[] files = dir.listFiles();
        if (files != null && files.length > 0) {
            for (File file : files) {
                if (file.isFile()) {
                    if (!file.delete()) return false;
                } else if (file.isDirectory()) {
                    if (!deleteDir(file)) return false;
                }
            }
        }
        return dir.delete();
    }

    /**
     * Write file from bytes by stream.
     *
     * @param file  The file.
     * @param bytes The bytes.
     * @return {@code true}: success<br>{@code false}: fail
     */
    public static boolean writeFileFromBytesByStream(final File file,
                                                     final byte[] bytes) {
        return writeFileFromBytesByStream(file, bytes, false, null);
    }

    /**
     * Write file from bytes by stream.
     *
     * @param file     The file.
     * @param bytes    The bytes.
     * @param listener The progress update listener.
     * @return {@code true}: success<br>{@code false}: fail
     */
    public static boolean writeFileFromBytesByStream(final File file,
                                                     final byte[] bytes,
                                                     final OnProgressUpdateListener listener) {
        return writeFileFromBytesByStream(file, bytes, false, listener);
    }

    /**
     * Write file from bytes by stream.
     *
     * @param file     The file.
     * @param bytes    The bytes.
     * @param append   True to append, false otherwise.
     * @param listener The progress update listener.
     * @return {@code true}: success<br>{@code false}: fail
     */
    public static boolean writeFileFromBytesByStream(final File file,
                                                     final byte[] bytes,
                                                     final boolean append,
                                                     final OnProgressUpdateListener listener) {
        if (bytes == null) return false;
        return writeFileFromIS(file, new ByteArrayInputStream(bytes), append, listener);
    }

    /**
     * Write file from input stream.
     *
     * @param file     The file.
     * @param is       The input stream.
     * @param append   True to append, false otherwise.
     * @param listener The progress update listener.
     * @return {@code true}: success<br>{@code false}: fail
     */
    public static boolean writeFileFromIS(final File file,
                                          final InputStream is,
                                          final boolean append,
                                          final OnProgressUpdateListener listener) {
        if (is == null || !createOrExistsFile(file)) {
            Log.e("FileIOUtils", "create file <" + file + "> failed.");
            return false;
        }
        OutputStream os = null;
        try {
            os = new BufferedOutputStream(new FileOutputStream(file, append), 524288);
            if (listener == null) {
                byte[] data = new byte[524288];
                for (int len; (len = is.read(data)) != -1; ) {
                    os.write(data, 0, len);
                }
            } else {
                double totalSize = is.available();
                int curSize = 0;
                listener.onProgressUpdate(0, curSize, totalSize);
                byte[] data = new byte[524288];
                for (int len; (len = is.read(data)) != -1; ) {
                    os.write(data, 0, len);
                    curSize += len;
                    listener.onProgressUpdate(curSize / totalSize, curSize, totalSize);
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static <T> byte[] toByteArray(T body) {
        if (body == null) {
            return new byte[]{};
        }
        ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(body);
            oos.flush();
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new byte[]{};
    }

    @Nullable
    public static Object toObject(byte[] data) {
        if (data == null) {
            return null;
        }
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
            bais = new ByteArrayInputStream(data);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bais != null) {
                try {
                    bais.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

}
