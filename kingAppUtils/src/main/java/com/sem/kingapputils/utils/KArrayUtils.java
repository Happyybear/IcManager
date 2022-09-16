package com.sem.kingapputils.utils;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: VQCStation
 * @Package: com.king.sem.utils
 * @ClassName: ArrayUtils
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2021/5/8 10:57
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/8 10:57
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class KArrayUtils {
    public static boolean isEmpty(List data){
        if (data == null || data.size() == 0){
            return true;
        }
        return false;
    }

    public static boolean isEqualObj(List<Object> data1 , List<Object> data2){
        if (isEmpty(data1)|| isEmpty(data2)){
            return false;
        }
        return Arrays.deepEquals(toArrayObj(data1), toArrayObj(data2));
    }

    public static boolean isEqual(List<Long> data1 , List<Long> data2){
        if (isEmpty(data1)|| isEmpty(data2)){
            return false;
        }
        return Arrays.deepEquals(toArray(data1), toArray(data2));
    }

    static Object[] toArrayObj(List<Object> data){
        if (!isEmpty(data)){
            Object[] array = new Object[data.size()];
            data.toArray(array);
            return array;
        }
        return null;
    }

    static Long[] toArray(List<Long> data){
        if (!isEmpty(data)){
            Long[] array = new Long[data.size()];
            data.toArray(array);
            return array;
        }
        return null;
    }

    public static boolean isEmpty(String[] data){
        if (data == null || data.length == 0){
            return true;
        }
        return false;
    }

    public static boolean isEmptyMap(Map data){
        if (data == null || data.size() == 0){
            return true;
        }
        return false;
    }

    public static byte[] getInitByteArray(byte value, int index){
        byte[] data = new  byte[index];
        for (int i = 0; i < index; i++) {
            data[i] = value;
        }
        return data;
    }
}
