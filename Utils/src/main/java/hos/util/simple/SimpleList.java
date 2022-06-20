package hos.util.simple;

import android.util.Pair;




import java.util.List;

import hos.util.compat.NetConstant;

/**
 * <p>Title: SimpleList </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2021/6/30 12:04
 */
public class SimpleList<ITEM> extends Simple<List<ITEM>> {

    @SafeVarargs
    public SimpleList(int code,  String message,  Pair<String, Object>... pairs) {
        super(code, message, pairs);
    }

    @SafeVarargs
    public SimpleList(int code,  String message, boolean isUpdate,  Pair<String, Object>... pairs) {
        super(code, message, isUpdate, pairs);
    }

    @SafeVarargs
    public SimpleList(int code,  String message,  List<ITEM> items,  Pair<String, Object>... pairs) {
        super(code, message, items, pairs);
    }

    @SafeVarargs
    public SimpleList(int code,  String message,  List<ITEM> items, boolean isUpdate,  Pair<String, Object>... pairs) {
        super(code, message, items, isUpdate, pairs);
    }

    @SafeVarargs
    public SimpleList(int code,  String message,  List<ITEM> items, int pageSize,  Pair<String, Object>... pairs) {
        super(code, message, items, pageSize, pairs);
    }

    @SafeVarargs
    public SimpleList(int code,  String message,  List<ITEM> items, int pageSize, boolean isUpdate,  Pair<String, Object>... pairs) {
        super(code, message, items, pageSize, isUpdate, pairs);
    }

    public long count() {
        if (data == null) {
            return 0;
        }
        return data.size();
    }

    @SafeVarargs
    public static <ITEM> SimpleList<ITEM> successList( String message,  List<ITEM> data,  Pair<String, Object>... pairs) {
        return new SimpleList<>(200, message, data, pairs);
    }

    @SafeVarargs
    public static <ITEM> SimpleList<ITEM> successList( String message,  List<ITEM> data, boolean isUpdate,  Pair<String, Object>... pairs) {
        return new SimpleList<>(200, message, data, isUpdate, pairs);
    }

    @SafeVarargs
    public static <ITEM> SimpleList<ITEM> successList( String message,  List<ITEM> data, int pageSize,  Pair<String, Object>... pairs) {
        return new SimpleList<>(200, message, data, pageSize, pairs);
    }

    @SafeVarargs
    public static <ITEM> SimpleList<ITEM> successList( String message,  List<ITEM> data, int pageSize, boolean isUpdate,  Pair<String, Object>... pairs) {
        return new SimpleList<>(200, message, data, pageSize, isUpdate, pairs);
    }

    @SafeVarargs
    public static <ITEM> SimpleList<ITEM> emptyList( String message,  Pair<String, Object>... pairs) {
        return new SimpleList<>(300, message, pairs);
    }

    @SafeVarargs
    public static <ITEM> SimpleList<ITEM> emptyList( String message, boolean isUpdate,  Pair<String, Object>... pairs) {
        return new SimpleList<>(300, message, isUpdate, pairs);
    }

    @SafeVarargs
    public static <ITEM> SimpleList<ITEM> errorList( String message,  Pair<String, Object>... pairs) {
        return new SimpleList<>(404, message, pairs);
    }

    @SafeVarargs
    public static <ITEM> SimpleList<ITEM> errorList( String message, boolean isUpdate,  Pair<String, Object>... pairs) {
        return new SimpleList<>(404, message, isUpdate, pairs);
    }

    @SafeVarargs
    public static <ITEM> SimpleList<ITEM> errorList( Pair<String, Object>... pairs) {
        return new SimpleList<>(404, NetConstant.SERVER_ERROR, pairs);
    }

    @SafeVarargs
    public static <ITEM> SimpleList<ITEM> errorList(boolean isUpdate,  Pair<String, Object>... pairs) {
        return new SimpleList<>(404, NetConstant.SERVER_ERROR, isUpdate, pairs);
    }

    @SafeVarargs
    public static <ITEM> SimpleList<ITEM> errorList(Throwable throwable,  Pair<String, Object>... pairs) {
        return new SimpleList<>(404, throwable.getMessage(), pairs);
    }

    @SafeVarargs
    public static <ITEM> SimpleList<ITEM> errorList(Throwable throwable, boolean isUpdate,  Pair<String, Object>... pairs) {
        return new SimpleList<>(404, throwable.getMessage(), isUpdate, pairs);
    }

    @SafeVarargs
    public static <ITEM> SimpleList<ITEM> netList(Throwable throwable,  Pair<String, Object>... pairs) {
        return new SimpleList<>(100, throwable.getMessage(), pairs);
    }

    @SafeVarargs
    public static <ITEM> SimpleList<ITEM> netList(Throwable throwable, boolean isUpdate,  Pair<String, Object>... pairs) {
        return new SimpleList<>(100, throwable.getMessage(), isUpdate, pairs);
    }

    @SafeVarargs
    public static <ITEM> SimpleList<ITEM> netList( Pair<String, Object>... pairs) {
        return new SimpleList<>(100, NetConstant.NETWORK_ERROR, pairs);
    }

    @SafeVarargs
    public static <ITEM> SimpleList<ITEM> netList(boolean isUpdate,  Pair<String, Object>... pairs) {
        return new SimpleList<>(100, NetConstant.NETWORK_ERROR, isUpdate, pairs);
    }
}
