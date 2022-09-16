package com.sem.kingapputils.check;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;


import java.util.Stack;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @ProjectName: sem
 * @Package: com.sem.kingapputils.check
 * @ClassName: KActivityManager
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2021/12/30 10:36
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/12/30 10:36
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class KActivityManager implements Application.ActivityLifecycleCallbacks {
    private static KActivityManager sInstance;
    private Stack<Activity> mActivityStack = new Stack<>();
    private Activity mCurrentActivity = null;


    @MainThread
    public static KActivityManager getInstance() {
        if (sInstance == null) {
            throw new IllegalAccessError("the KActivityManager is not initialized; " +
                    "please call KActivityManager.init(Application) in your application.");
        }
        return sInstance;
    }

    private KActivityManager() {
    }

    public static void init(@NonNull Application application) {
        if (sInstance == null) {
            sInstance = new KActivityManager();
            application.registerActivityLifecycleCallbacks(sInstance);
        }
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, Bundle savedInstanceState) {
        if(mCurrentActivity == null){
            mCurrentActivity = activity;
        }
        mActivityStack.add(activity);
    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {
        mActivityStack.remove(activity);
        if(mActivityStack.isEmpty()){
            mCurrentActivity = null;
        }
    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        mCurrentActivity = activity;
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Nullable
    public Activity getCurrentActivity(){
        return mCurrentActivity;
    }

    public int getActivityCount(){
        return mActivityStack.size();
    }

    @Nullable
    public Activity getActivityInStack(int index){
        if(index < 0 || index >= mActivityStack.size()){
            return null;
        }
        return mActivityStack.get(index);
    }

    /**
     *
     * refer to https://github.com/bingoogolapple/BGASwipeBackLayout-Android/
     * @param currentActivity the last activity
     * @return
     */
    @Nullable
    public Activity getPenultimateActivity(Activity currentActivity) {
        Activity activity = null;
        try {
            if (mActivityStack.size() > 1) {
                activity = mActivityStack.get(mActivityStack.size() - 2);

                if (currentActivity.equals(activity)) {
                    int index = mActivityStack.indexOf(currentActivity);
                    if (index > 0) {
                        // if memory leaks or the last activity is being finished
                        activity = mActivityStack.get(index - 1);
                    } else if (mActivityStack.size() == 2) {
                        // if screen orientation changes, there may be an error sequence in the stack
                        activity = mActivityStack.lastElement();
                    }
                }
            }
        } catch (Exception ignored) {
        }
        return activity;
    }

    public void finishAllActivity(){
        mCurrentActivity = null;
        while (!mActivityStack.empty()) {
            Activity activity = mActivityStack.pop();
            activity.finish();
        }
        mActivityStack.clear();
    }
}
