package com.sem.kingapputils.check;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;

/**
 * @ProjectName: WanAndroid
 * @Package: per.goweii.wanandroid.module.check
 * @ClassName: ApplicationInstrumentation
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2021/9/26 13:58
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/9/26 13:58
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ApplicationInstrumentation extends Instrumentation {
    private static final String TAG = "ApplicationInstrumentation";

    // ActivityThread中原始的对象, 保存起来
    Instrumentation mBase;

    public ApplicationInstrumentation(Instrumentation base) {
        mBase = base;
    }

    @Override
    public Activity newActivity(ClassLoader cl, String className,
                                Intent intent)
            throws InstantiationException, IllegalAccessException,
            ClassNotFoundException {
        className = CheckApp.getApp().getActivityName(className);
        return mBase.newActivity(cl, className, intent);
    }

}
