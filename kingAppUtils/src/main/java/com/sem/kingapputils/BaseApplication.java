package com.sem.kingapputils;

import android.app.Application;
import android.util.Log;
import android.view.Gravity;

import com.hjq.toast.ToastUtils;
import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager;
import com.sem.kingapputils.check.CheckApp;
import com.sem.kingapputils.check.KActivityManager;
import com.sem.kingapputils.ui.toast.KToastNormalStyle;
import com.sem.kingapputils.utils.Utils;
import com.tencent.mmkv.MMKV;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

/**
 * @ProjectName: VQCStation
 * @Package: com.king.sem.app
 * @ClassName: BaseApplication
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2021/5/8 9:40
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/8 9:40
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BaseApplication extends CheckApp implements ViewModelStoreOwner {

    private ViewModelStore mAppViewModelStore;

    @Override
    public final void onCreate() {
        super.onCreate();
        initPara();
        mAppViewModelStore = new ViewModelStore();
        Log.d("BaseApplication","onCreate");
    }

    protected void initPara() {
    }

    @Override
    protected void initSafeSDK() {
        // 初始化 Toast 框架
        ToastUtils.init(this);
        ToastUtils.setStyle(new KToastNormalStyle());
        ToastUtils.setGravity(Gravity.BOTTOM, 0, 80);
        MMKV.initialize(this);
        QMUISwipeBackActivityManager.init(this);
        KActivityManager.init(this);
    }

    @Override
    protected void initSDK() {
    }

    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return mAppViewModelStore;
    }
}
