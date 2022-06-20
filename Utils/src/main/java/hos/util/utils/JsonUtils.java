package hos.util.utils;




import java.util.List;
import java.util.Map;

/**
 * <p>Title: JsonUtils </p>
 * <p>Description: Json转换 </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2022/5/12 22:15
 */
public class JsonUtils {


    public static String toJsonList( List<Map<String, Object>> mapList) {
        if (mapList == null || mapList.isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Map<String, Object> map : mapList) {
            String json = toJson(map);
            sb.append(json);
        }
        sb.append("]");
        return sb.toString();
    }


    public static String toJson( List<Object> list) {
        if (list == null || list.isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Object root = list.get(0);
        if (root instanceof List) {
            for (Object newList : list) {
                String json = toJson((List<Object>) newList);
                sb.append("[").append(json).append("]").append(",");
            }
            sb.deleteCharAt(sb.lastIndexOf(","));
        } else if (root instanceof Map) {
            for (Object mapList : list) {
                String json = toJson((Map<String, Object>) mapList);
                sb.append(json);
            }
        } else {
            for (Object object : list) {
                sb.append("'").append(object).append("'").append(",");
            }
            sb.deleteCharAt(sb.lastIndexOf(","));
        }
        sb.append("]");
        return sb.toString();
    }


    public static String toJson( Map<String, Object> mapList) {
        if (mapList == null || mapList.isEmpty()) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (String key : mapList.keySet()) {
            Object object = mapList.get(key);
            Object value = object;
            if (object instanceof List) {
                value = toJson((List<Object>) object);
            } else if (object instanceof Map) {
                value = toJson((Map<String, Object>) object);
            }
            sb.append(key).append(":'").append(value).append("'").append(",");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("}");
        return sb.toString();
    }


}
