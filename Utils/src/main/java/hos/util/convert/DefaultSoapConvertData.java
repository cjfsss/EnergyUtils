package hos.util.convert;

import android.text.TextUtils;




import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;

import hos.util.utils.SoapUtils;
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
public abstract class DefaultSoapConvertData extends DefaultConvertData {
    public DefaultSoapConvertData(String success, String code, String message, String data) {
        super(success, code, message, data);
    }

    public DefaultSoapConvertData(String success, String code, String message) {
        super(success, code, message);
    }

    public DefaultSoapConvertData(String success, String code) {
        super(success, code);
    }

    public DefaultSoapConvertData(String success) {
        super(success);
    }

    public DefaultSoapConvertData() {
        super();
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


    @Override
    public String handleBody( Object body) {
        return SoapUtils.clearXml(super.handleBody(body));
    }
}

