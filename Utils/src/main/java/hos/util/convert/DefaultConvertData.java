package hos.util.convert;

import android.text.TextUtils;




import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import hos.util.utils.StringUtils;

/**
 * <p>Title: DefaultConvert </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2021/10/12 20:47
 */
public abstract class DefaultConvertData extends DefaultConvert {
    protected final String data;

    public DefaultConvertData(String success, String code, String message, String data) {
        super(success, code, message);
        this.data = data;
    }

    public DefaultConvertData(String success, String code, String message) {
        this(success, code, message, "data");
    }

    public DefaultConvertData(String success, String code) {
        this(success, code, "message", "data");
    }

    public DefaultConvertData(String success) {
        this(success, "state", "message", "data");
    }

    public DefaultConvertData() {
        this("0", "state", "message", "data");
    }


    @Override
    public <R> R convertBody( Object response,  String handleBody,  Type succeed) throws JSONException, IOException {
        JSONObject jsonObject = new JSONObject(handleBody);// 获取JSON中后端定义的错误码和错误信息
        if (TextUtils.equals(jsonObject.getString(this.code), success)) { // 对比后端自定义错误码
            return parseBody(StringUtils.toEmpty(jsonObject.get(data)), succeed);
        } else { // 错误码匹配失败, 开始写入错误异常
            throw new ConvertException(jsonObject.getString(message), 100);
        }
    }


    public abstract <R> R parseBody( String handleBody,  Type succeed);
}

