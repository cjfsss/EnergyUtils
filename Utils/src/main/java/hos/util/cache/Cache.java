package hos.util.cache;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.Serializable;

/**
 * <p>Title: Cache </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2022/4/2 20:45
 */
public class Cache implements Serializable {
    @NonNull
    private String key;
    @Nullable
    private byte[] data;

    public Cache(@NonNull String key) {
        this.key = key;
    }

    public Cache(@NonNull String key, @Nullable byte[] data) {
        this.key = key;
        this.data = data;
    }

    @NonNull
    public String getKey() {
        return key;
    }

    public void setKey(@NonNull String key) {
        this.key = key;
    }

    @Nullable
    public byte[] getData() {
        return data;
    }

    public void setData(@Nullable byte[] data) {
        this.data = data;
    }
}
