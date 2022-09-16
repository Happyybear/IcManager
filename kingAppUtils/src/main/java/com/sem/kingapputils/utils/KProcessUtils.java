package com.sem.kingapputils.utils;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @ProjectName: sem
 * @Package: com.sem.kingapputils.utils
 * @ClassName: KProcessUtils
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2021/9/26 16:12
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/9/26 16:12
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class KProcessUtils {
    public static String getProcessNameByReflect(Context context) {
        Object activityThread = getActivityThread(context);
        if (activityThread != null) {
            try {
                Method method = activityThread.getClass().getMethod("currentProcessName");
                method.setAccessible(true);
                String value = (String) method.invoke(activityThread);
                return value;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    private static Object getActivityThread(Context context) {
        Object activityThread = getActivityThreadInActivityThreadStaticField();
        if (activityThread != null) return activityThread;
        activityThread = getActivityThreadInActivityThreadStaticMethod();
        if (activityThread != null) return activityThread;
        return getActivityThreadInLoadedApkField(context);
    }

    private static Object getActivityThreadInActivityThreadStaticField() {
        try {
            Class activityThreadClass = Class.forName("android.app.ActivityThread");
            Field sCurrentActivityThreadField = activityThreadClass.getDeclaredField("sCurrentActivityThread");
            sCurrentActivityThreadField.setAccessible(true);
            return sCurrentActivityThreadField.get(null);
        } catch (Exception e) {
            Log.e("ProcessUtils", "getActivityThreadInActivityThreadStaticField: " + e.getMessage());
            return null;
        }
    }

    private static Object getActivityThreadInActivityThreadStaticMethod() {
        try {
            Class activityThreadClass = Class.forName("android.app.ActivityThread");
            return activityThreadClass.getMethod("currentActivityThread").invoke(null);
        } catch (Exception e) {
            Log.e("ProcessUtils", "getActivityThreadInActivityThreadStaticMethod: " + e.getMessage());
            return null;
        }
    }

    private static Object getActivityThreadInLoadedApkField(Context context) {
        try {
            Field mLoadedApkField = Application.class.getDeclaredField("mLoadedApk");
            mLoadedApkField.setAccessible(true);
            Object mLoadedApk = mLoadedApkField.get(context);
            Field mActivityThreadField = mLoadedApk.getClass().getDeclaredField("mActivityThread");
            mActivityThreadField.setAccessible(true);
            return mActivityThreadField.get(mLoadedApk);
        } catch (Exception e) {
            Log.e("UtilsActivityLifecycle", "getActivityThreadInLoadedApkField: " + e.getMessage());
            return null;
        }
    }
}
