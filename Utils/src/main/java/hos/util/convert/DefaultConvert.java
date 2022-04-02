package hos.util.convert;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * <p>Title: DefaultConvert </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2021/10/12 20:47
 */
public abstract class DefaultConvert extends NetConverter.DEFAULT {
    protected final String success;
    protected final String code;
    protected final String message;

    public DefaultConvert(String success, String code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public DefaultConvert(String success, String code) {
        this(success, code, "message");
    }

    public DefaultConvert(String success) {
        this(success, "state", "message");
    }

    public DefaultConvert() {
        this("0", "state", "message");
    }

    @Nullable
    @Override
    public <R> R convertBody(@NonNull Object response, @NonNull String handleBody, @NonNull Type succeed) throws JSONException, IOException {
        JSONObject jsonObject = new JSONObject(handleBody);// 获取JSON中后端定义的错误码和错误信息
        if (TextUtils.equals(jsonObject.getString(this.code), success)) { // 对比后端自定义错误码
            return parseBody(handleBody, succeed);
        } else { // 错误码匹配失败, 开始写入错误异常
            throw new ConvertException(jsonObject.getString(message), 100);
        }
    }

    @Nullable
    public abstract <R> R parseBody(@NonNull String handleBody, @NonNull Type succeed);
}

