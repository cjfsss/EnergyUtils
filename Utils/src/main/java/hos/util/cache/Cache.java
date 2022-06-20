package hos.util.cache;




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
    
    private String key;
    
    private byte[] data;

    public Cache( String key) {
        this.key = key;
    }

    public Cache( String key,  byte[] data) {
        this.key = key;
        this.data = data;
    }

    
    public String getKey() {
        return key;
    }

    public void setKey( String key) {
        this.key = key;
    }

    
    public byte[] getData() {
        return data;
    }

    public void setData( byte[] data) {
        this.data = data;
    }
}
