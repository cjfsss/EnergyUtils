package hos.util.utils;

import androidx.annotation.Nullable;

/**
 * <p>Title: SoapUtils </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2021/4/20 17:46
 */
public class SoapUtils {

    @Nullable
    public static String clearXml(@Nullable String body) {
        if (body == null || body.length() == 0) {
            return null;
        }
        return body.replaceAll("<?xml version=\"1.0\" encoding=\"utf-8\"?>", "")
                .replaceAll("\n", "")
                .replaceAll("\t", "")
                .replaceAll("\r", "")
                .replaceAll("<?xml version=\"1.0\" encoding=\"utf-8\"?>", "")
                .replaceAll("<boolean xmlns=\"http://tempuri.org/\">", "")
                .replaceAll("<long xmlns=\"http://tempuri.org/\">", "")
                .replaceAll("<double xmlns=\"http://tempuri.org/\">", "")
                .replaceAll("<int xmlns=\"http://tempuri.org/\">", "")
                .replaceAll("<string xmlns=\"http://tempuri.org/\">", "")
                .replaceAll("</string>", "")
                .replaceAll("</int>", "")
                .replaceAll("</double>", "")
                .replaceAll("</long>", "")
                .replaceAll("</boolean>", "")
                .replaceAll("</xml>", "").trim();
    }
}
