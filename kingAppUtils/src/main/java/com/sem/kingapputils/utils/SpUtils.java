package com.sem.kingapputils.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @ProjectName: sem
 * @Package: com.sem.kingapputils.utils
 * @ClassName: SpUtils
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2021/9/26 17:07
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/9/26 17:07
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class SpUtils {

    private static final String SEM_SP_KEY = "SEM_SP_KEY_K_APP";

    private static SharedPreferences getSp(Context context){
        return context.getSharedPreferences(SEM_SP_KEY, Context.MODE_PRIVATE);
    }

    public static void putBooleanValue(Context context, boolean value, String key){
        SharedPreferences sp = getSp(context);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getBooleanValue(Context context, String key, boolean defaultValue){
        SharedPreferences editor = getSp(context);
        return editor.getBoolean(key, defaultValue);
    }
}
