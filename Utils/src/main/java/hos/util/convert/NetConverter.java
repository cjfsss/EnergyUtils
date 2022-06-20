package hos.util.convert;




import org.json.JSONException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.util.Objects;

import hos.util.utils.StringUtils;

/**
 * <p>Title: NetConverter </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2021/10/12 20:22
 */
public interface NetConverter {


    <R> R onConvert( Type succeed,  Object response) throws JSONException, IOException;

    class DEFAULT implements NetConverter {

        @SuppressWarnings("unchecked")
        @Override
        public <R> R onConvert( Type succeed,  Object response) throws JSONException, IOException {
            if (response == null || handleBody(response) == null) {
                return null;
            }
            if (succeed == String.class) {
                return (R) response.toString();
            }
            if (succeed == Integer.class) {
                return (R) Integer.valueOf(response.toString());
            }
            if (succeed == Long.class) {
                return (R) Long.valueOf(response.toString());
            }
            if (succeed == Float.class) {
                return (R) Float.valueOf(response.toString());
            }
            if (succeed == Double.class) {
                return (R) Double.valueOf(response.toString());
            }
            if (succeed == File.class) {
                return (R) response;
            }
            if (succeed instanceof GenericArrayType &&succeed == Byte.class) {
                return (R) response;
            }
            if (succeed == Boolean.class) {
                return (R) Boolean.valueOf(response.toString());
            }
            return convertBody(response, Objects.requireNonNull(handleBody(response)), Objects.requireNonNull(succeed));
        }


        public <R> R convertBody( Object response,  String handleBody, Type succeed) throws JSONException, IOException {
            return null;
        }


        public String handleBody( Object body) {
            return StringUtils.toNULL(body);
        }
    }
}
