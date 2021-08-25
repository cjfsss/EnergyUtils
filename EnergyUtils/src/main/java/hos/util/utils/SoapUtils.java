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
                .replaceAll("<string xmlns=\"http://tempuri.org/\">", "")
                .replaceAll("</string>", "")
                .replaceAll("\n", "")
                .replaceAll("\t", "")
                .replaceAll("\r", "").trim();
    }
}
