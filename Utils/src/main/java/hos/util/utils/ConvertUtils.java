package hos.util.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>Title: ConvertUtils </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2021/4/2 16:00
 */
public class ConvertUtils {

    /**
     * 短整型与字节的转换
     */
    public static final byte[] shortToByte(short number) {
        int temp = number;
        byte[] b = new byte[2];
        for (int i = 0; i < b.length; i++) {
            // 将最低位保存在最低位
            b[i] = new Integer(temp & 0xff).byteValue();
            // 向右移8位
            temp = temp >> 8;
        }
        return b;
    }

    /**
     * 字节的转换与短整型
     */
    public static final short byteToShort(byte[] b) {
        short s;
        // 最低位
        short s0 = (short) (b[0] & 0xff);
        short s1 = (short) (b[1] & 0xff);
        s1 <<= 8;
        s = (short) (s0 | s1);
        return s;
    }

    /**
     * 整型与字节数组的转换
     */
    public static final byte[] intToByte(int i) {
        byte[] bt = new byte[4];
        bt[0] = (byte) (0xff & i);
        bt[1] = (byte) ((0xff00 & i) >> 8);
        bt[2] = (byte) ((0xff0000 & i) >> 16);
        bt[3] = (byte) ((0xff000000 & i) >> 24);
        return bt;
    }

    /**
     * 字节数组和整型的转换
     */
    public static final int bytesToInt(byte[] bytes) {
        int num = bytes[0] & 0xFF;
        num |= ((bytes[1] << 8) & 0xFF00);
        num |= ((bytes[2] << 16) & 0xFF0000);
        num |= ((bytes[3] << 24) & 0xFF000000);
        return num;
    }

    /**
     * 整型数组转换为字节数组的转换
     *
     * @param arr 整型数组
     */
    public static final byte[] intToByte(int[] arr) {
        byte[] bt = new byte[arr.length * 4];
        for (int i = 0; i < arr.length; i++) {
            byte[] t = intToByte(arr[i]);
            System.arraycopy(t, 0, bt, i + 4, 4);
        }
        return bt;
    }


    /**
     * 字节数组和长整型的转换
     */
    public static final byte[] longToByte(long number) {
        long temp = number;
        byte[] b = new byte[8];
        for (int i = 0; i < b.length; i++) {
            b[i] = new Long(temp & 0xff).byteValue();
            // 将最低位保存在最低位
            temp = temp >> 8;
            // 向右移8位
        }
        return b;
    }

    /**
     * 字节数组和长整型的转换
     */
    public static final long byteToLong(byte[] b) {
        long s;
        long s0 = b[0] & 0xff;// 最低位
        long s1 = b[1] & 0xff;
        long s2 = b[2] & 0xff;
        long s3 = b[3] & 0xff;
        long s4 = b[4] & 0xff;// 最低位
        long s5 = b[5] & 0xff;
        long s6 = b[6] & 0xff;
        long s7 = b[7] & 0xff; // s0不变
        s1 <<= 8;
        s2 <<= 16;
        s3 <<= 24;
        s4 <<= 8 * 4;
        s5 <<= 8 * 5;
        s6 <<= 8 * 6;
        s7 <<= 8 * 7;
        s = s0 | s1 | s2 | s3 | s4 | s5 | s6 | s7;
        return s;
    }

    /**
     * 转换为Integer列表<br>
     *
     * @param split 分隔符
     * @param split 被转换的值
     * @return 结果
     */
    public static List<Integer> toIntList(String str, String split) {
        List<Integer> list = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return list;
        }
        String[] arr = str.split(split);
        for (String ss : arr) {
            list.add(Integer.parseInt(ss));
        }
        return list;
    }

    /**
     * 將字字符串分割成Long類型的列表
     *
     * @param str
     * @return
     */
    public static List<Long> toLongList(String str, String split) {
        List<Long> list = new ArrayList<>();
        String[] splits = str.split(split);
        for (String s : splits) {
            list.add(Long.parseLong(s));
        }
        return list;
    }


    /**
     * 将数组转换为list
     *
     * @param array
     * @param <T>
     * @return
     */
    public static <T> List<T> arrayToList(T[] array) {
        ArrayList<T> arrayList = new ArrayList<>(array.length);
        Collections.addAll(arrayList, array);
        return arrayList;
    }

}
