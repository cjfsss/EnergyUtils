package hos.util.utils;


/**
 * <p>Title: IDUtils </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2021/4/1 23:26
 */
public class IDUtils {
    /**
     * 图片名生成
     */
    public static String genImageName() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //如果不足三位前面补0
        return millis +  NumberUtils.number(3);
    }
    /**
     * 商品id生成
     */
    public static long genItemId() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //加上两位随机数
        //如果不足两位前面补0
        String str = millis + NumberUtils.number(2);
        return Long.parseLong(str);
    }
    /**
     * UUID生成
     * @return
     */
    public static String genUUID() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        String uuid = NumberUtils.uuid();
        //加上三位随机数
        NumberUtils.number(3);
        //如果不足两位前面补0
        return uuid + millis + NumberUtils.number(3);
    }
}
