package com.sem.kingapputils.check;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.sem.kingapputils.utils.KProcessUtils;
import com.sem.kingapputils.utils.SharedPreferenceManager;
import com.sem.kingapputils.utils.SpUtils;


/**
 * @ProjectName: WanAndroid
 * @Package: per.goweii.wanandroid.module.check
 * @ClassName: CheckApp
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2021/9/26 13:59
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/9/26 13:59
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class CheckApp extends Application {
    /**
     * 用户是否同意隐私协议 
     */
    private static final String KEY_USER_AGREE = CheckApp.class.getName() + "_key_user_agree";
    private static final String KEY_CHECK_ACTIVITY = "com.trs.GuideActivity";

    private boolean userAgree;

    private static CheckApp app;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        userAgree = SpUtils.getBooleanValue(base, getUserAgreeKey(base), false);
        getCheckActivityName(base);
        if (!userAgree) {
            //只有在用户不同意的情况下才hook ，避免性能损失
            try {
                HookUtil.attachContext();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    protected String getUserAgreeKey(Context base) {
        if (checkForEachVersion()) {
            try {
                long longVersionCode = base.getPackageManager().getPackageInfo(base.getPackageName(), 0).versionCode;
                return KEY_USER_AGREE + "_version_" + longVersionCode;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return KEY_USER_AGREE;

    }

    /**
     * 是否每个版本都检查是否拥有用户隐私权限
     * @return
     */
    protected boolean checkForEachVersion() {
        return false;
    }

    private static boolean initSDK = false;//是否已经初始化了SDK

    String checkActivityName = null;

    private void getCheckActivityName(Context base) {
        PackageManager mPackageManager = base.getPackageManager();
        try {
            ApplicationInfo appInfo = mPackageManager.getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            checkActivityName = appInfo.metaData.getString(KEY_CHECK_ACTIVITY);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        checkActivityName = checkName(checkActivityName);

    }

    public String getActivityName(String name) {
        if (isUserAgree()) {
            return name;
        } else {
            setRealFirstActivityName(name);
            return checkActivityName;
        }
    }

    private String checkName(String name) {
        String newName = name;
        if (!newName.startsWith(".")) {
            newName = "." + newName;
        }
        if (!name.startsWith(getPackageName())) {
            newName = getPackageName() + newName;
        }

        return newName;

    }


    @Override
    public void onCreate() {
        super.onCreate();
        if (!isRunOnMainProcess()) {
            return;
        }
        app = this;
        initSafeSDK();

        //初始化那些和隐私无关的SDK
        if (userAgree && !initSDK) {
            initSDK = true;
            initSDK();
        }

    }


    public static CheckApp getApp() {
        return app;
    }


    /**
     * 初始化那些和用户隐私无关的SDK
     * 如果无法区分，建议只使用initSDK一个方法
     */
    protected void initSafeSDK() {

    }


    /**
     * 判断用户是否同意
     *
     * @return
     */
    public boolean isUserAgree() {
        return userAgree;
    }

//    static PackageManager mPackageManager;

    private static String realFirstActivityName = null;

    public static void setRealFirstActivityName(String realFirstActivityName) {
        CheckApp.realFirstActivityName = realFirstActivityName;
    }

    public void agree(Activity activity, boolean gotoFirstActivity, Bundle extras) {

        SpUtils.putBooleanValue(activity,true, getUserAgreeKey(this));
        userAgree = true;

        if (!initSDK) {
            initSDK = true;
            initSDK();
        }

        //启动真正的启动页
        if (!gotoFirstActivity) {
            //已经是同一个界面了，不需要自动打开
            return;
        }
        try {
            Intent intent = new Intent(activity, Class.forName(realFirstActivityName));
            if (extras != null) {
                intent.putExtras(extras);//也许是从网页中调起app，这时候extras中含有打开特定新闻的参数。需要传递给真正的启动页
            }
            activity.startActivity(intent);
            activity.finish();// 关闭当前页面
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    /**
     * 子类重写用于初始化SDK等相关工作
     */
    abstract protected void initSDK();

    /**
     * 判断是否在主进程中，一些SDK中的PushServer可能运行在其他进程中。
     * 也就会造成Application初始化两次,而只有在主进程中才需要初始化。
     * * @return
     */
    public boolean isRunOnMainProcess() {
        String mainProcessName = this.getPackageName();
        String processNameByReflect = KProcessUtils.getProcessNameByReflect(this);
        if ( mainProcessName.equals(processNameByReflect)) {
            return true;
        }
        return false;
    }
}
