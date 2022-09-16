package com.sem.kingapputils.check;

import android.app.Instrumentation;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ProjectName: WanAndroid
 * @Package: per.goweii.wanandroid.module.check
 * @ClassName: HookUtil
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2021/9/26 14:01
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/9/26 14:01
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class HookUtil {
    public static void attachContext() throws Exception {
        Log.i("zzz", "attachContext: ");
        // 先获取到当前的ActivityThread对象
        Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
        Method currentActivityThreadMethod = activityThreadClass.getDeclaredMethod("currentActivityThread");
        currentActivityThreadMethod.setAccessible(true);
        //currentActivityThread是一个static函数所以可以直接invoke，不需要带实例参数
        Object currentActivityThread = currentActivityThreadMethod.invoke(null);

        // 拿到原始的 mInstrumentation字段
        Field mInstrumentationField = activityThreadClass.getDeclaredField("mInstrumentation");
        mInstrumentationField.setAccessible(true);
        Instrumentation mInstrumentation = (Instrumentation) mInstrumentationField.get(currentActivityThread);
        // 创建代理对象
        Instrumentation evilInstrumentation = new ApplicationInstrumentation(mInstrumentation);
        // 偷梁换柱
        mInstrumentationField.set(currentActivityThread, evilInstrumentation);
    }

    private static WifiInfo cacheWifiInfo = null;

    public static void hookMacAddress(String tag, Context context) {
        try {
            Class<?> iWifiManager = Class.forName("android.net.wifi.IWifiManager");
            Field serviceField = WifiManager.class.getDeclaredField("mService");
            serviceField.setAccessible(true);

            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            // real mService
            Object realIwm = serviceField.get(wifi);
            // replace mService  with Proxy.newProxyInstance
            serviceField.set(wifi, Proxy.newProxyInstance(iWifiManager.getClassLoader(),
                    new Class[]{iWifiManager},
                    new InvocationHandler(tag, "getConnectionInfo", realIwm)));
            Log.i(tag, "wifiManager hook success");
        } catch (Exception e) {
            Log.e(tag, "printStackTrace:" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static class InvocationHandler implements java.lang.reflect.InvocationHandler {

        private final String tag;
        private final String methodName;
        private Object real;

        public InvocationHandler(String tag, String methodName, Object real) {
            this.real = real;
            this.methodName = methodName;
            this.tag = tag;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Log.d(tag, "method invoke " + method.getName());
            if (methodName.equals(method.getName())) {
                if (cacheWifiInfo != null) {
                    Log.d(tag, "cacheWifiInfo:" + cacheWifiInfo);
                    return cacheWifiInfo;
                }
                WifiInfo wifiInfo = null;
                try {
                    Class cls = WifiInfo.class;
                    wifiInfo = (WifiInfo) cls.newInstance();
                    Field mMacAddressField = cls.getDeclaredField("mMacAddress");
                    mMacAddressField.setAccessible(true);
                    mMacAddressField.set(wifiInfo, "");
                    cacheWifiInfo = wifiInfo;
                    Log.d(tag, "wifiInfo:" + wifiInfo);
                } catch (Exception e) {
                    Log.e(tag, "WifiInfo error:" + e.getMessage());
                }
                return wifiInfo;
            } else {
                return method.invoke(real, args);
            }
        }
    }
}
