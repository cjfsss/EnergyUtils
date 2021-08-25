package hos.util.utils;

import android.annotation.SuppressLint;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.ArrayMap;
import android.util.Base64;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.text.HtmlCompat;

import java.io.UnsupportedEncodingException;
import java.math.RoundingMode;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>Title: StringUtils </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2020/1/1 23:26
 */
public class StringUtils {

    /**
     * 下划线
     */
    private static final char SEPARATOR = '_';
    public static final Pattern PATTER_BLANK_CODE = Pattern.compile("\\s*|\t|\r|\n");

    @SuppressLint({"HardwareIds", "MissingPermission"})
    @NonNull
    public static String getUUIDByPhoneInfo() {
        String serial;
        String m_szDevIDShort = "35" +
                Build.BOARD.length() % 10 + Build.BRAND.length() % 10 +

                Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 +

                Build.DISPLAY.length() % 10 + Build.HOST.length() % 10 +

                Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 +

                Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 +

                Build.TAGS.length() % 10 + Build.TYPE.length() % 10 +

                Build.USER.length() % 10; //13 位

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                serial = Build.getSerial();
            } else {
                serial = Build.SERIAL;
            }
            //API>=9 使用serial号
            return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
        } catch (Exception exception) {
            //serial需要一个初始化
            serial = "serial"; // 随便一个初始化
        }
        //使用硬件信息拼凑出来的15位号码
        return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
    }

    /**
     * 获取md5的UUID
     */
    @NonNull
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    @NonNull
    public static String getyyyyMMdd() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMdd");
        return formatDate.format(new Date());
    }

    @SuppressWarnings("SpellCheckingInspection")
    @NonNull
    public static String getyyyyMMddHHmmssSSSS() {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
        return formatDate.format(new Date());
    }

    /**
     * 根据指定的字符把源字符串分割成一个list
     *
     * @param src     处理的字符串
     * @param pattern 分割字符串
     * @return 处理后的list
     */
    public static List<String> parseString2List(String src, String pattern) {
        List<String> list = new ArrayList<>();
        if (toNULL(src) != null) {
            String[] tt = src.split(pattern);
            list.addAll(Arrays.asList(tt));
        }
        return list;
    }


    /**
     * 获取已,隔开的第一个
     */
    @NonNull
    public static String getFirstSplit(@NonNull String target) {
        if (toNULL(target) == null) {
            return "";
        }
        if (target.contains(",")) {
            return target.split(",")[0];
        }
        return target;
    }

    /**
     * 获取已,隔开的排除第一个
     *
     * @param target 目标字符串
     */
    public static String getExcludeFirstSplit(@NonNull String target) {
        if (toNULL(target) == null) {
            return "";
        }
        if (target.contains(",")) {
            String[] split = target.split(",");
            StringBuilder builder = new StringBuilder();
            for (int i = 1; i < split.length; i++) {
                builder.append(split[i]).append(",");
            }
            return StringBuilderUtils.deleteEnd(builder).toString();
        }
        return "";
    }

    /**
     * 截取字符串左侧指定长度的字符串
     *
     * @param input 输入字符串
     * @param count 截取长度
     * @return 截取字符串
     */
    public static String left(String input, int count) {
        if (toNULL(input) == null) {
            return "";
        }
        count = Math.min(count, input.length());
        return input.substring(0, count);
    }

    /**
     * 截取字符串右侧指定长度的字符串
     *
     * @param input 输入字符串
     * @param count 截取长度
     * @return 截取字符串
     * Summary 其他编码的有待测试
     */
    public static String right(String input, int count) {
        if (toNULL(input) == null) {
            return "";
        }
        count = Math.min(count, input.length());
        return input.substring(input.length() - count, input.length());
    }

    /**
     * 页面中去除字符串中的空格、回车、换行符、制表符
     *
     * @param str 需要处理的字符串
     */
    public static String replaceBlank(String str) {
        if (str != null) {
            Matcher m = PATTER_BLANK_CODE.matcher(str);
            str = m.replaceAll("");
        }
        return str;
    }

    /**
     * 下划线转驼峰命名
     */
    public static String toUnderScoreCase(String s) {
        if (s == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            boolean nextUpperCase = true;

            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }

            if ((i > 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }

            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }


    /**
     * 对字符串进行编码
     *
     * @param target 目标
     * @return
     */
    @NonNull
    public static CharSequence toBase64Encoder(@Nullable CharSequence target) {
        target = toEmpty(target);
        if (TextUtils.isEmpty(target)) {
            return target;
        }
        return Base64.encodeToString(target.toString().getBytes(Charset.defaultCharset()), Base64.NO_WRAP);
    }

    /**
     * 对字符串进行解密
     *
     * @param target 目标值
     * @return
     */
    @NonNull
    public static CharSequence toBase64Decoder(@Nullable CharSequence target) {
        target = toEmpty(target);
        if (TextUtils.isEmpty(target)) {
            return target;
        }
        return new String(Base64.decode(target.toString().getBytes(Charset.defaultCharset()), Base64.NO_WRAP), Charset.defaultCharset());
    }

    /**
     * 对字符串进行编码
     *
     * @param target 目标
     * @return
     */
    @NonNull
    public static CharSequence toBase64UrlEncoder(@Nullable CharSequence target) {
        target = toEmpty(target);
        if (TextUtils.isEmpty(target)) {
            return target;
        }
        final String base64 = Base64.encodeToString(target.toString().getBytes(Charset.defaultCharset()), Base64.NO_WRAP);
        try {
            return URLEncoder.encode(base64, Charset.defaultCharset().name());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return target;
    }

    /**
     * 对字符串进行解密
     *
     * @param target 目标值
     * @return
     */
    @NonNull
    public static CharSequence toUrlBase64Decoder(@Nullable CharSequence target) {
        target = toEmpty(target);
        if (TextUtils.isEmpty(target)) {
            return target;
        }
        try {
            final String base64 = URLDecoder.decode(target.toString(), Charset.defaultCharset().name());
            return new String(Base64.decode(base64.getBytes(Charset.defaultCharset()), Base64.NO_WRAP), Charset.defaultCharset());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return target;
    }


    /**
     * 确定时间中的T和.后面的数字
     *
     * @param time 时间
     */
    @NonNull
    public static String toTime(@Nullable String time) {
        if (time == null || TextUtils.isEmpty(time)) {
            return "";
        }
        if (time.contains("T") || time.contains("+") || time.contains(".")) {
            if (time.contains("T")) {
                time = time.replace("T", " ");
            }
            if (time.contains(".")) {
                time = time.substring(0, time.lastIndexOf("."));
            }
            if (time.contains("+")) {
                time = time.substring(0, time.lastIndexOf("+"));
            }
            return time;
        }
        return time;
    }

    /**
     * 确定时间中的T和.后面的数字
     *
     * @param time 时间
     */
    @NonNull
    public static String toTimeN(@Nullable String time) {
        String toTime = toTime(time);
        if (TextUtils.isEmpty(toTime)) {
            return toTime;
        }
        if (toTime.contains("/")) {
            toTime = toTime.replace("/", "-");
        }
        if (!toTime.contains(".")) {
            return toTime;
        }
        return toTime.substring(0, toTime.lastIndexOf("."));
    }

    @NonNull
    public static String toStringZero(@Nullable String value) {
        if (value == null || TextUtils.isEmpty(toNULL(value))) {
            return "0";
        }
        if (value.indexOf(".") > 0) {
            value = value.replaceAll("0+?$", "");//去掉多余的0
            value = value.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return value;
    }

    /**
     * 将String转换为Long类型
     *
     * @param value 目标值
     */
    public static long toLongZero(@Nullable final String value) {
        if (value == null || TextUtils.isEmpty(toNULL(value))) {
            return 0L;
        }
        return Long.parseLong(value);
    }

    /**
     * 将String转换为Int类型
     *
     * @param value 目标值
     */
    public static int toIntZero(@Nullable final String value) {
        if (value == null || TextUtils.isEmpty(toNULL(value))) {
            return 0;
        }
        return Integer.parseInt(value);
    }

    /**
     * 将String转换为float类型
     *
     * @param value 目标值
     */
    public static float toFloatZero(@Nullable final String value) {
        if (value == null || TextUtils.isEmpty(toNULL(value))) {
            return 0f;
        }
        return Float.parseFloat(value);
    }

    /**
     * 将String转换为double类型
     *
     * @param value 目标值
     */
    public static double toDoubleZero(@Nullable final String value) {
        if (value == null || TextUtils.isEmpty(toNULL(value))) {
            return 0.0D;
        }
        return Double.parseDouble(value);
    }


    /**
     * 去除html中的空格
     *
     * @param html 目标
     */
    @NonNull
    public static String htmlDeleteSpace(@NonNull String html) {
        if (TextUtils.isEmpty(html)) {
            return "";
        }
        if (html.contains("&nbsp;")) {
            html = html.replaceAll("&nbsp;", " ");
        }
        return html.trim();
    }

    /**
     * 从连接地址中获取Url
     *
     * @param url 访问地址
     */
    public static String getGetUrl(@NonNull String url) {
        if (TextUtils.isEmpty(url)) {
            return "";
        }
        if (url.contains("?")) {
            return url.split("\\?")[0];
        }
        return url;
    }

    /**
     * 从连接地址中获取Url
     *
     * @param url 访问地址
     */
    public static Map<String, String> getGetUrlParams(@NonNull String url) {
        Map<String, String> map = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            map = new ArrayMap<>();
        } else {
            map = new HashMap<>();
        }
        if (TextUtils.isEmpty(url)) {
            return map;
        }
        if (url.contains("?")) {
            String paramStr = url.split("\\?")[1];
            if (paramStr.contains("&")) {
                String[] params = paramStr.split("&");
                for (String param : params) {
                    if (param.contains("=")) {
                        String[] paramSplit = param.split("=");
                        if (paramSplit.length == 1) {
                            map.put(paramSplit[0], null);
                        } else {
                            map.put(paramSplit[0], paramSplit[1]);
                        }
                    }
                }
            } else {
                if (paramStr.contains("=")) {
                    String[] paramSplit = paramStr.split("=");
                    if (paramSplit.length == 1) {
                        map.put(paramSplit[0], null);
                    } else {
                        map.put(paramSplit[0], paramSplit[1]);
                    }
                }
            }
        } else {
            if (url.contains("&")) {
                String[] params = url.split("&");
                for (String param : params) {
                    if (param.contains("=")) {
                        String[] paramSplit = param.split("=");
                        if (paramSplit.length == 1) {
                            map.put(paramSplit[0], null);
                        } else {
                            map.put(paramSplit[0], paramSplit[1]);
                        }
                    }
                }
            } else {
                if (url.contains("=")) {
                    String[] paramSplit = url.split("=");
                    if (paramSplit.length == 1) {
                        map.put(paramSplit[0], null);
                    } else {
                        map.put(paramSplit[0], paramSplit[1]);
                    }
                }
            }
        }
        return map;
    }

    @Nullable
    public static String toNULL(@Nullable CharSequence target) {
        if (target == null || TextUtils.isEmpty(target) || TextUtils.equals(target, "null")
                || TextUtils.equals(target, "NULL")) {
            return null;
        }
        return target.toString();
    }

    @NonNull
    public static String toEmpty(@Nullable CharSequence target) {
        if (toNULL(target) == null) {
            return "";
        }
        return target == null ? "" : target.toString();
    }

    @NonNull
    public static String trimNR(@Nullable String target) {
        if (target == null || TextUtils.isEmpty(toNULL(target))) {
            return "";
        }
        return target.replaceAll("\n", "")
                .replaceAll("\t", "")
                .replaceAll("\r", "").trim();
    }

    @NonNull
    public static String noData(@Nullable CharSequence target) {
        CharSequence toNULL = toNULL(target);
        if (toNULL == null) {
            return "暂无";
        }
        return toNULL.toString();
    }

    @NonNull
    public static String noData_(@Nullable CharSequence target) {
        CharSequence toNULL = toNULL(target);
        if (toNULL == null) {
            return "-";
        }
        return toNULL.toString();
    }

    @NonNull
    public static String noDataTime(@Nullable CharSequence target) {
        CharSequence toNULL = toNULL(target);
        if (toNULL == null) {
            return "暂无";
        }
        return toTime(toNULL.toString());
    }

    @NonNull
    public static String noDataTimeN(@Nullable CharSequence target) {
        CharSequence toNULL = toNULL(target);
        if (toNULL == null) {
            return "暂无";
        }
        return toTimeN(toNULL.toString());
    }

    @NonNull
    public static String deleteZeroNoData_(@Nullable CharSequence target) {
        CharSequence toNULL = toNULL(target);
        if (toNULL == null || TextUtils.equals(toNULL, "-99")) {
            return "-";
        }
        String deleteEndZero = deleteEndZero(toNULL);
        if (TextUtils.equals(deleteEndZero, "-99")) {
            return "-";
        }
        return deleteEndZero;
    }

    @NonNull
    public static String deleteZeroDecimalNoData_(@Nullable CharSequence target) {
        CharSequence toNULL = toNULL(target);
        if (toNULL == null) {
            return "-";
        }
        String deleteEndZero = toDecimalFormat("#", toNULL);
        if (TextUtils.equals(deleteEndZero, "-99")) {
            return "-";
        }
        return deleteEndZero;
    }

    @NonNull
    public static String deleteZeroDecimalNoData_(@NonNull final String pattern, @Nullable CharSequence target) {
        CharSequence toNULL = toNULL(target);
        if (toNULL == null) {
            return "-";
        }
        String deleteEndZero = toDecimalFormat(pattern, toNULL);
        if (TextUtils.equals(deleteEndZero, "-99")) {
            return "-";
        }
        return deleteEndZero;
    }

    @NonNull
    public static String deleteEndZero(@Nullable CharSequence target) {
        String str = toEmpty(target).toString();
        if (str.indexOf(".") > 0) {
            // 去掉多余的0
            str = str.replaceAll("0+?$", "");
            // 如最后一位是.则去掉
            str = str.replaceAll("[.]$", "");
        }
        return str;
    }

    /**
     * 对入参保留最多两位小数(舍弃末尾的0)，如:
     * 3.345->3.34
     * 3.40->3.4
     * 3.0->3
     */
    @NonNull
    public static String toDecimalFormat(@Nullable final Object number) {
        return toDecimalFormat("0.##", number);
    }

    /**
     * 对入参保留最多几位小数(舍弃末尾的0)，如:
     * 3.345->3.34
     * 3.40->3.4
     * 3.0->3
     */
    @NonNull
    public static String toDecimalFormat(@NonNull final String pattern, @Nullable final Object number) {
        if (number == null) {
            return "0";
        }
        DecimalFormat format = new DecimalFormat(pattern);
        //未保留小数的舍弃规则，RoundingMode.FLOOR表示直接舍弃。
        format.setRoundingMode(RoundingMode.FLOOR);
        return format.format(number);
    }

    @NonNull
    public static SpannableStringBuilder color(@NonNull String start, @NonNull String content,
                                               @ColorInt int colorContent) {
        return color(start, content, null, colorContent);
    }

    @NonNull
    public static SpannableStringBuilder color(@NonNull String start, @NonNull String content,
                                               @Nullable String end, @ColorInt int colorContent) {
        final String text = start + content + toEmpty(end);
        // 设置文字的颜色
        SpannableStringBuilder textBuilder = new SpannableStringBuilder(content);
        textBuilder.setSpan(
                new ForegroundColorSpan(colorContent),
                text.indexOf(content), text.indexOf(content) + content.length(),
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        );
        return textBuilder;
    }

    @NonNull
    public static String convertUnicode(String ori) {
        if (ori == null) {
            return "";
        }
        char aChar;
        int len = ori.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = ori.charAt(x++);
            if (aChar == '\\') {
                aChar = ori.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = ori.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);

        }
        return outBuffer.toString();
    }

    @NonNull
    public static Spanned fromHtml(@NonNull String source) {
        return HtmlCompat.fromHtml(source, HtmlCompat.FROM_HTML_MODE_LEGACY);
    }

    @NonNull
    public static CharSequence like(@Nullable CharSequence charSequence) {
        if (toNULL(charSequence) == null) {
            return "%%";
        }
        return "%" + charSequence + "%";
    }

    @NonNull
    public static String urlHttp(@Nullable String url) {
        url = toEmpty(url);
        if (url.length() == 0) {
            return "";
        }
        if (url.contains("http://")) {
            return url;
        }
        return "http://" + url;
    }

    @NonNull
    public static String urlHttps(@Nullable String url) {
        url = toEmpty(url);
        if (url.length() == 0) {
            return "";
        }
        if (url.contains("https://")) {
            return url;
        }
        return "https://" + url;
    }

    /**
     * Return whether the string is null or white space.
     *
     * @param s The string.
     * @return {@code true}: yes<br> {@code false}: no
     */
    public static boolean isSpace(final String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    @NonNull
    public static String getEmpty(@NonNull String target, @Nullable String value) {
        String toNull = toNULL(target);
        if (toNull == null) {
            return value == null ? "" : value;
        }
        return toNull;
    }

    @NonNull
    public static String getEmpty(@NonNull String target) {
        return getEmpty(target, "");
    }

    public static int getInt(@NonNull String target, int value) {
        String toNull = toNULL(target);
        if (toNull == null) {
            return value;
        }
        try {
            return Integer.parseInt(toNull);
        } catch (NumberFormatException e) {
            return value;
        }
    }

    public static int getInt(@NonNull String target) {
        return getInt(target, 0);
    }

    public static long getLong(@NonNull String target, long value) {
        String toNull = toNULL(target);
        if (toNull == null) {
            return value;
        }
        try {
            return Long.parseLong(toNull);
        } catch (NumberFormatException e) {
            return value;
        }
    }

    public static long getLong(@NonNull String target) {
        return getLong(target, 0L);
    }

    public static double getDouble(@NonNull String target, double value) {
        String toNull = toNULL(target);
        if (toNull == null) {
            return value;
        }
        try {
            return Double.parseDouble(StringUtils.deleteEndZero(toNull));
        } catch (NumberFormatException e) {
            return value;
        }
    }

    public static double getDouble(@NonNull String target) {
        return getDouble(target, 0D);
    }

    @NonNull
    public static String getRate(@NonNull String target, @Nullable String defaultValue) {
        String value = toNULL(target);
        if (value == null) {
            if (defaultValue == null || defaultValue.length() == 0) {
                return "-";
            }
            return defaultValue;
        }
        if (value.contains("%")) {
            return value;
        }
        return StringUtils.deleteEndZero(value) + "%";
    }

    @NonNull
    public static String getRate(@NonNull String target) {
        return getRate(target, "-");
    }

}
