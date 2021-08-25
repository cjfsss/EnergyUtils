package hos.util.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import hos.util.listener.bean.BeanEnd;
import hos.util.listener.bean.BeanExpand;
import hos.util.listener.bean.BeanPosition;

import java.util.List;

/**
 * <p>Title: ListUtlis </p>
 * <p>Description: 集合工具类 </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2021/3/22 14:21
 */
public class ListUtils {

    public static <T> String split(@Nullable List<T> list, @NonNull String split) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        final StringBuilder builder = new StringBuilder();
        final int size = list.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                builder.append(split);
            }
            builder.append(list.get(i));
        }
        return builder.toString();
    }

    public static <T> String split(@Nullable T[] list, @NonNull String split) {
        if (list == null || list.length == 0) {
            return "";
        }
        final StringBuilder builder = new StringBuilder();
        final int size = list.length;
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                builder.append(split);
            }
            builder.append(list[i]);
        }
        return builder.toString();
    }

    public static <T> void appendPosition(@Nullable List<T> list, int groupPosition) {
        if (list == null || list.isEmpty()) {
            return;
        }
        final int size = list.size();
        for (int i = 0; i < size; i++) {
            T item = list.get(i);
            if (item == null) {
                continue;
            }
            if (item instanceof BeanPosition) {
                ((BeanPosition) item).setPosition(i);
            } else if (item instanceof BeanEnd && i == (size - 1)) {
                ((BeanEnd) item).setEnd(true);
            }
            if (item instanceof BeanExpand) {
                BeanExpand expand = (BeanExpand) item;
                expand.setGroupPosition(groupPosition);
                appendPosition(expand.getSublist(), i);
            }
        }
    }
}
