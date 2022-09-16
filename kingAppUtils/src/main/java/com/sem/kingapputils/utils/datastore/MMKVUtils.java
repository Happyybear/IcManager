package com.sem.kingapputils.utils.datastore;

import android.app.Service;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sem.kingapputils.utils.KArrayUtils;
import com.sem.kingapputils.utils.KSetUtils;
import com.sem.kingapputils.utils.StringUtils;
import com.tencent.mmkv.MMKV;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @ProjectName: sem
 * @Package: com.sem.kingapputils.utils
 * @ClassName: MMKVUtils
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2021/8/12 16:58
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/8/12 16:58
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class MMKVUtils {

    public static<T> boolean setSetObject(String key, Set<T> value){
        MMKV mmkv = MMKV.defaultMMKV();
        if (!KSetUtils.isEmpty(value)) {
            Gson gson = new Gson();
            String s = gson.toJson(value);
            if (!TextUtils.isEmpty(s)) {
                return mmkv.encode(key, s);
            }
        }
        return false;
    }

    public static <T> Set<T> getSetObject(String key){
        MMKV mmkv = MMKV.defaultMMKV();
        String value = mmkv.decodeString(key);
        if (!TextUtils.isEmpty(value)){
            java.lang.reflect.Type type = new TypeToken<Set<T>>() {
            }.getType();
            Gson gson = new Gson();
            return gson.fromJson(value, type);
        }
        return null;
    }

    public static<T> boolean setListObject(String key, List<T> value){
        MMKV mmkv = MMKV.defaultMMKV();
        if (!KArrayUtils.isEmpty(value)) {
            Gson gson = new Gson();
            String s = gson.toJson(value);
            if (!TextUtils.isEmpty(s)) {
                return mmkv.encode(key, s);
            }
        }
        return false;
    }


    public static <T> List<T> getListObject(String key){
        MMKV mmkv = MMKV.defaultMMKV();
        String value = mmkv.decodeString(key);
        if (!TextUtils.isEmpty(value)){
            Gson gson = new Gson();
            return gson.fromJson(value, List.class);
//            return Arrays.asList(longData);
        }
        return null;
    }

    public static boolean setListLong(String key, List<Long> value){
        MMKV mmkv = MMKV.defaultMMKV();
        if (!KArrayUtils.isEmpty(value)) {
            Gson gson = new Gson();
            String s = gson.toJson(value);
            if (!TextUtils.isEmpty(s)) {
                return mmkv.encode(key, s);
            }
        }
        return false;
    }

    public static List<Long> getListLongValue(String key){
        MMKV mmkv = MMKV.defaultMMKV();
        String value = mmkv.decodeString(key);
        if (!TextUtils.isEmpty(value)){
            Gson gson = new Gson();
            Long[] longData= gson.fromJson(value, Long[].class);
            return Arrays.asList(longData);
        }
        return null;
    }

    public static void saveListData(String key, List<Long> data){
        MMKV mmkv = MMKV.defaultMMKV();
        if (!KArrayUtils.isEmpty(data)) {
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < data.size(); i++) {
                Long aLong = data.get(i);
                jsonArray.put(aLong);
            }
            mmkv.encode(key,jsonArray.toString());
        }
    }

    public static List<Long> getListLongData(String key){
        List<Long> data = new ArrayList<>();
        MMKV mmkv = MMKV.defaultMMKV();
        String s = mmkv.decodeString(key, null);
        if (StringUtils.isEmpty(s)){
            return data;
        }
        try {
            JSONArray array = new JSONArray(s);
            for (int i = 0; i < array.length(); i++) {
                Object o = array.get(i);
                if (o instanceof Long) {
                    data.add((Long) o);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static Long getLongData(String key){
        MMKV mmkv = MMKV.defaultMMKV();
        return mmkv.decodeLong(key, 0);
    }

    public static boolean putLong(String Key, Long value) {
        MMKV mmkv = MMKV.defaultMMKV();
        return mmkv.encode(Key,value);
    }

    public static boolean putInt(String Key, int value) {
        MMKV mmkv = MMKV.defaultMMKV();
        return mmkv.encode(Key,value);
    }

    public static Integer getIntData(String key){
        MMKV mmkv = MMKV.defaultMMKV();
        return mmkv.decodeInt(key, 0);
    }

    public static boolean putFloat(String Key, float value) {
        MMKV mmkv = MMKV.defaultMMKV();
        return mmkv.encode(Key,value);
    }

    public static float getFloatData(String key){
        MMKV mmkv = MMKV.defaultMMKV();
        return mmkv.decodeFloat(key, 0f);
    }

    public static Boolean putBoolean(String Key, boolean value) {
        MMKV mmkv = MMKV.defaultMMKV();
        return mmkv.encode(Key,value);
    }

    public static boolean getBoolean(String Key) {
        MMKV mmkv = MMKV.defaultMMKV();
        return mmkv.decodeBool(Key, false);
    }

}
