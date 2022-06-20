package hos.util.utils;


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

    /**
     * 将Double转换为double类型
     *
     * @param value 目标值
     */
    public static double toDoubleZero(final Double value) {
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
    public static double toDecimalFormat(final Double number) {
        return Double.parseDouble(StringUtils.toDecimalFormat(number));
    }

    /**
     * 对入参保留最多几位小数(舍弃末尾的0)，如:
     * 3.345->3.34
     * 3.40->3.4
     * 3.0->3
     */
    public static double toDecimalFormat(final String pattern, final Double number) {
        return Double.parseDouble(StringUtils.toDecimalFormat(pattern, number));
    }


    public static String noData_(Double target) {
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
    public static float toFloatZero(final Float value) {
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
    public static float toDecimalFormat(final Float number) {
        return Float.parseFloat(StringUtils.toDecimalFormat(number));
    }

    /**
     * 对入参保留最多几位小数(舍弃末尾的0)，如:
     * 3.345->3.34
     * 3.40->3.4
     * 3.0->3
     */
    public static float toDecimalFormat(final String pattern, final Float number) {
        return Float.parseFloat(StringUtils.toDecimalFormat(pattern, number));
    }


    public static String noData_(Float target) {
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
    public static int toIntZero(final Integer value) {
        if (value == null) {
            return 0;
        }
        return value;
    }


    public static String noData_(Integer target) {
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
    public static long toLongZero(final Long value) {
        if (value == null) {
            return 0L;
        }
        return value;
    }


    public static String noData_(Long target) {
        if (target == null) {
            return "-";
        }
        return String.valueOf(toLongZero(target));
    }
}
