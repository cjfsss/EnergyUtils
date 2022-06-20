package hos.util.utils;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

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


    public static String uuid16() {
        String uuid = UUID.randomUUID().toString();
        char[] cs = new char[32];
        char c = 0;
        for (int i = uuid.length() / 2, j = 1; i-- > 0; ) {
            if ((c = uuid.charAt(i)) != '-') {
                cs[j++] = c;
            }
        }
        return String.valueOf(cs);
    }

    /**
     * 返回一个UUID
     *
     * @return 小写的UUID
     */
    public static String uuid() {
        return UUID.randomUUID().toString();

    }

    /**
     * 返回一个UUID
     *
     * @return 大写的UUID
     */
    public static String UUID() {
        return UUID.randomUUID().toString().toUpperCase();
    }

    /**
     * 返回一个有序列的uuid编码
     * 前11位为时间(毫秒)
     * 中间4位为主机特征码
     * 剩下的保证其唯一性
     *
     * @param hostFeature 主机特征码建议设置4位
     * @return
     */
    public static String squid(String hostFeature) {
        long date = System.currentTimeMillis();
        String s = UUID.randomUUID().toString();
        String str = Long.toHexString(date);
        String result = str + hostFeature
                + s.substring(17, 18) + s.substring(19, 23) + s.substring(24);
        return result.toUpperCase();
    }


    /**
     * 图片名生成
     */
    public static String genImageName() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        //如果不足三位前面补0
        return millis + NumberUtils.number(3);
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
     * Time + UUID 生成
     *
     * @return
     */
    public static String genTimeUUID() {
        //取当前时间的长整形值包含毫秒
        long millis = System.currentTimeMillis();
        String uuid = uuid();
        //加上三位随机数
        NumberUtils.number(3);
        //如果不足两位前面补0
        return uuid + millis + NumberUtils.number(3);
    }

    public static String nanoId() {
        return NanoIdUtils.randomNanoId();
    }

    public static String dateId() {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String time = format.format(new Date(System.currentTimeMillis()));
        if (time.contains("-")) {
            time = time.replaceAll("-", "");
        }
        if (time.contains(" ")) {
            time = time.replaceAll(" ", "");
        }
        if (time.contains(".")) {
            time = time.replaceAll("\\.", "");
        }
        return time;
    }

    public static String genDateId() {
        return dateId() + NumberUtils.number(3);
    }

    public static String genNanoId() {
        return nanoId() + NumberUtils.number(3);
    }

    public static String genDateNanoId() {
        return dateId() + nanoId() + NumberUtils.number(3);
    }

}
