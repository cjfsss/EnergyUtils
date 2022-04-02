package hos.util.convert;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONException;

import java.io.File;
import java.io.IOException;
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

    @Nullable
    <R> R onConvert(@Nullable Type succeed, @Nullable Object response) throws JSONException, IOException;

    class DEFAULT implements NetConverter {

        @SuppressWarnings("unchecked")
        @Override
        public <R> R onConvert(@Nullable Type succeed, @Nullable Object response) throws JSONException, IOException {
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
            if (succeed == Byte.class) {
                return (R) Byte.valueOf(response.toString());
            }
            if (succeed == Boolean.class) {
                return (R) Boolean.valueOf(response.toString());
            }
            return convertBody(response, Objects.requireNonNull(handleBody(response)), Objects.requireNonNull(succeed));
        }

        @Nullable
        public <R> R convertBody(@NonNull Object response, @NonNull String handleBody,@NonNull Type succeed) throws JSONException, IOException {
            return null;
        }

        @Nullable
        public String handleBody(@Nullable Object body) {
            return StringUtils.toNULL(body);
        }
    }
}
