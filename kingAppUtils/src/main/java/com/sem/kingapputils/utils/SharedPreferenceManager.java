package com.sem.kingapputils.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;


import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SharedPreferenceManager {

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public SharedPreferenceManager(String spName) {
        sp = Utils.getApp().getSharedPreferences(spName, Context.MODE_PRIVATE);
        editor = sp.edit();
        editor.apply();
    }

    public void clear(String ket){
		editor.remove(ket).apply();
	}
    /**
     * 获取是key
     * @return
     * @param key
     */
    public Boolean getBoolen(String key){
        return getBoolenValue(key,false);
    }

    private Boolean getBoolenValue(String key, boolean defValue){
        return sp.getBoolean(key,defValue);
    }

    /**
     * SP中写入boolean类型value
     * @param key
     * @param value
     */
    public void putBoolen(String key,Boolean value){
        putBoolenValue(key,value);
    }

    private void putBoolenValue(String key, boolean defValue){
         editor.putBoolean(key,defValue).apply();
    }

	/**
	 * SP中写入int[]类型value
	 * @param key
	 * @param value
	 */
	public void putListInt(String key, int[] value){
		putListValue(key,value);
	}

	private void putListValue(String key, int[] defValue){
		JSONArray jsonArray = new JSONArray();
		for (int b : defValue) {
			jsonArray.put(b);
		}
		editor.putString(key,jsonArray.toString()).apply();
	}
	/**
	 * SP中写入Map类型value
	 * @param key
	 * @param value
	 */
	public void putStringMap(String key, Map value){
		JSONArray jsonArray = new JSONArray();
		Iterator<Map.Entry<String, String>> iterator = value.entrySet().iterator();
		JSONObject object = new JSONObject();
		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = iterator.next();
			try {
				object.put(entry.getKey(), entry.getValue());
				jsonArray.put(object);
			} catch (JSONException e) {
				return;
			}
		}
		editor.putString(key,jsonArray.toString()).apply();
		editor.apply();
	}

	public HashMap<String, String> getStringMapValue(String key) {
		LinkedHashMap<String, String> datas = new LinkedHashMap<>();
		String result = sp.getString(key, "");
		try {
			JSONArray array = new JSONArray(result);
			for (int i = 0; i < array.length(); i++) {
				JSONObject itemObject = array.getJSONObject(i);
				JSONArray names = itemObject.names();
				if (names != null) {
					for (int j = 0; j < names.length(); j++) {
						String name = names.getString(j);
						String value = itemObject.getString(name);
						datas.put(name, value);
					}
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return datas;
	}
	/**
	 * SP中写读取nt[]类型value
	 * @param key
	 */
	public int[] getListInt(String key,int len){
		return getListIntValue(key,len);
	}

	private int[] getListIntValue(String key,int len){
		int[] resArray=new int[len];
		try {
			JSONArray jsonArray = new JSONArray(sp.getString(key, "[]"));
			for (int i = 0; i < jsonArray.length(); i++) {
				resArray[i] = jsonArray.getInt(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resArray;
	}


	public static SharedPreferences getSharePreFerences(String name,Context context) {

		return context.getSharedPreferences(name, 0);
	}

}
