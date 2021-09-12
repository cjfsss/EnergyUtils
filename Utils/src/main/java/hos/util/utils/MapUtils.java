package hos.util.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import hos.util.listener.bean.BeanEnd;
import hos.util.listener.bean.BeanExpand;
import hos.util.listener.bean.BeanPosition;

/**
 * <p>Title: MapUtils </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2021/3/22 14:39
 */
public class MapUtils {
    /**
     * 将map转换为List
     *
     * @return List
     */
    @NonNull
    public static <K, V> List<K> toListKey(@Nullable Map<K, V> map) {
        if (map == null || map.isEmpty()) {
            return new LinkedList<>();
        }
        final Set<K> keySet = map.keySet();
        return new LinkedList<>(keySet);
    }

    /**
     * 将map转换为List
     *
     * @return List
     */
    @NonNull
    public static <K, V> List<V> toListValue(@Nullable Map<K, V> map) {
        if (map == null || map.isEmpty()) {
            return new ArrayList<V>();
        }
        final List<V> list = new ArrayList<>();
        final Set<K> keySet = map.keySet();
        final int size = keySet.size();
        int i = 0;
        for (K key : keySet) {
            V item = map.get(key);
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
                ListUtils.appendPosition(expand.getSublist(), i);
            }
            list.add(item);
            i++;
        }
        return list;
    }

    @Nullable
    public static <K, V> String toNull(@Nullable Map<K, V> map, @NonNull String key) {
        if (map == null) {
            return null;
        }
        @Nullable Object object = map.get(key);
        if (object == null) {
            return null;
        }
        return StringUtils.toNULL(object);
    }

    @Nullable
    public static <K, V> String get(@Nullable Map<K, V> map, @NonNull String key, @Nullable String value) {
        String toNull = get(map, key);
        if (toNull == null) {
            return value;
        }
        return toNull;
    }

    @Nullable
    public static <K, V> String get(@Nullable Map<K, V> map, @NonNull String key) {
        return toNull(map, key);
    }

    @NonNull
    public static <K, V> String getEmpty(@Nullable Map<K, V> map, @NonNull String key, @Nullable String value) {
        String toNull = get(map, key, value);
        if (toNull == null) {
            return value == null ? "" : value;
        }
        return toNull;
    }

    @NonNull
    public static <K, V> String getEmpty(@Nullable Map<K, V> map, @NonNull String key) {
        return getEmpty(map, key, "");
    }

    public static <K, V> int getInt(@Nullable Map<K, V> map, @NonNull String key, int value) {
        String toNull = get(map, key);
        if (toNull == null) {
            return value;
        }
        try {
            return Integer.parseInt(toNull);
        } catch (NumberFormatException e) {
            return value;
        }
    }

    public static <K, V> int getInt(@Nullable Map<K, V> map, @NonNull String key) {
        return getInt(map, key, 0);
    }

    public static <K, V> long getLong(@Nullable Map<K, V> map, @NonNull String key, long value) {
        String toNull = get(map, key);
        if (toNull == null) {
            return value;
        }
        try {
            return Long.parseLong(toNull);
        } catch (NumberFormatException e) {
            return value;
        }
    }

    public static <K, V> long getLong(@Nullable Map<K, V> map, @NonNull String key) {
        return getLong(map, key, 0L);
    }

    public static <K, V> double getDouble(@Nullable Map<K, V> map, @NonNull String key, double value) {
        String toNull = get(map, key);
        if (toNull == null) {
            return value;
        }
        try {
            return Double.parseDouble(StringUtils.deleteEndZero(toNull));
        } catch (NumberFormatException e) {
            return value;
        }
    }

    public static <K, V> double getDouble(@Nullable Map<K, V> map, @NonNull String key) {
        return getDouble(map, key, 0D);
    }

    @NonNull
    public static <K, V> String getRate(@Nullable Map<K, V> map, @NonNull String key, @Nullable String defaultValue) {
        String value = toNull(map, key);
        if (value == null) {
            if (defaultValue == null || defaultValue.length() == 0) {
                return "-";
            }
            return defaultValue;
        }
        if (value.contains("%")) {
            return value;
        }
        return StringUtils.deleteEndZero(value) + "%";
    }

    @NonNull
    public static <K, V> String getRate(@Nullable Map<K, V> map, @NonNull String key) {
        return getRate(map, key, "-");
    }

    @Nullable
    public static <K, V> String getTime(@Nullable Map<K, V> map, @NonNull String key, @Nullable String defaultValue) {
        String value = toNull(map, key);
        if (value == null) {
            return defaultValue;
        }
        return StringUtils.toTime(value);
    }

    @Nullable
    public static <K, V> String getTime(@Nullable Map<K, V> map, @NonNull String key) {
        return getTime(map, key, null);
    }
}
