package com.sem.kingapputils.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager;

import java.util.List;

/**
 * @ProjectName: sem
 * @Package: com.sem.kingapputils.utils
 * @ClassName: AppRunUtils
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2021/12/24 9:11
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/12/24 9:11
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class AppRunUtils {

    /**
     * 判断资源是否被回收
     * @return result
     */
    public static boolean checkAppResourceRecycle(){
        // 判读 == 1，是因为super.onCreate(savedInstanceState);时已经将activity添加到stack中了
        return QMUISwipeBackActivityManager.getInstance().getActivityCount() == 1;
    }

    public static void reStart(Context context){
        Log.d("AppRunUtils","startAPP");
        final Intent intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        //杀掉以前进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    public static boolean checkAppRun() {
        ActivityManager am = (ActivityManager) Utils.getApp().getSystemService(Context.ACTIVITY_SERVICE);
        @SuppressWarnings("deprecation")
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(10);
        for (ActivityManager.RunningTaskInfo info : list) {
            if (info.topActivity.getPackageName().equals("com.tsr.ele_manager")
                    && info.baseActivity.getPackageName().equals("com.tsr.ele_manager")) {
                return true;
            }
        }
        return false;

    }

    public static Context getTopActivityOrApp() {
        Activity currentActivity = QMUISwipeBackActivityManager.getInstance().getCurrentActivity();
        if (currentActivity == null){
            return Utils.getApp();
        }else {
            return currentActivity;
        }
    }

    public static void sendLoginTimeout(Context context){
        if (context != null) {
            Intent intent = new Intent("com.tsr.broadcast.LoginOverdueBroastcast");
            context.sendBroadcast(intent);
        }
    }
}
