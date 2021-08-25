package hos.util.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Random;
import java.util.UUID;

/**
 * <p>Title: NumberUtils </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2021/4/2 15:49
 */
public class NumberUtils {
    public static final String ALLCHAR
            = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LETTERCHAR
            = "abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUMBERCHAR
            = "0123456789";


    /**
     * 生成制定范围内的随机数
     *
     * @param scopeMin
     * @param scoeMax
     * @return
     */
    public static int integer(int scopeMin, int scoeMax) {
        Random random = new Random();
        return (random.nextInt(scoeMax) % (scoeMax - scopeMin + 1) + scopeMin);
    }

    /**
     * 返回固定长度的数字
     *
     * @param length
     * @return
     */
    public static String number(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(NUMBERCHAR.charAt(random.nextInt(NUMBERCHAR.length())));
        }
        return sb.toString();
    }

    /**
     * 返回一个定长的随机字符串(只包含大小写字母、数字)
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String string(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
        }
        return sb.toString();
    }

    /**
     * 返回一个定长的随机纯字母字符串(只包含大小写字母)
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String mixString(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(ALLCHAR.charAt(random.nextInt(LETTERCHAR.length())));
        }
        return sb.toString();
    }

    /**
     * 返回一个定长的随机纯大写字母字符串(只包含大小写字母)
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String lowerString(int length) {
        return mixString(length).toLowerCase();
    }

    /**
     * 返回一个定长的随机纯小写字母字符串(只包含大小写字母)
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String upperString(int length) {
        return mixString(length).toUpperCase();
    }

    /**
     * 生成一个定长的纯0字符串
     *
     * @param length 字符串长度
     * @return 纯0字符串
     */
    public static String zeroString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append('0');
        }
        return sb.toString();
    }
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
     * 将Double转换为double类型
     *
     * @param value 目标值
     */
    public static double toDoubleZero(@Nullable final Double value) {
        if (value == null) {
            return 0.0D;
        }
        return value;
    }

    /**
     * 对入参保留最多两位小数(舍弃末尾的0)，如:
     * 3.345->3.34
     * 3.40->3.4
     * 3.0->3
     */
    public static double toDecimalFormat(@Nullable final Double number) {
        return Double.parseDouble(StringUtils.toDecimalFormat(number));
    }

    /**
     * 对入参保留最多几位小数(舍弃末尾的0)，如:
     * 3.345->3.34
     * 3.40->3.4
     * 3.0->3
     */
    public static double toDecimalFormat(@NonNull final String pattern, @Nullable final Double number) {
        return Double.parseDouble(StringUtils.toDecimalFormat(pattern, number));
    }

    @NonNull
    public static String noData_(@Nullable Double target) {
        if (target == null) {
            return "-";
        }
        return StringUtils.deleteEndZero(String.valueOf(toDoubleZero(target)));
    }


    /**
     * 将Float转换为float类型
     *
     * @param value 目标值
     */
    public static float toFloatZero(@Nullable final Float value) {
        if (value == null) {
            return 0f;
        }
        return value;
    }

    /**
     * 对入参保留最多两位小数(舍弃末尾的0)，如:
     * 3.345->3.34
     * 3.40->3.4
     * 3.0->3
     */
    public static float toDecimalFormat(@Nullable final Float number) {
        return Float.parseFloat(StringUtils.toDecimalFormat(number));
    }

    /**
     * 对入参保留最多几位小数(舍弃末尾的0)，如:
     * 3.345->3.34
     * 3.40->3.4
     * 3.0->3
     */
    public static float toDecimalFormat(@NonNull final String pattern, @Nullable final Float number) {
        return Float.parseFloat(StringUtils.toDecimalFormat(pattern, number));
    }

    @NonNull
    public static String noData_(@Nullable Float target) {
        if (target == null) {
            return "-";
        }
        return StringUtils.deleteEndZero(String.valueOf(toFloatZero(target)));
    }

    /**
     * 将Integer转换为int类型
     *
     * @param value 目标值
     */
    public static int toIntZero(@Nullable final Integer value) {
        if (value == null) {
            return 0;
        }
        return value;
    }

    @NonNull
    public static String noData_(@Nullable Integer target) {
        if (target == null) {
            return "-";
        }
        return String.valueOf(toIntZero(target));
    }
    /**
     * 将Long转换为long类型
     *
     * @param value 目标值
     */
    public static long toLongZero(@Nullable final Long value) {
        if (value == null) {
            return 0L;
        }
        return value;
    }

    @NonNull
    public static String noData_(@Nullable Long target) {
        if (target == null) {
            return "-";
        }
        return String.valueOf(toLongZero(target));
    }
}
