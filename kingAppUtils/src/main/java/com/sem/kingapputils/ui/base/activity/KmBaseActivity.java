package com.sem.kingapputils.ui.base.activity;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.hjq.toast.ToastUtils;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;
import com.sem.kingapputils.BaseApplication;
import com.sem.kingapputils.data.response.manager.NetworkStateManager;
import com.sem.kingapputils.utils.AdaptScreenUtils;
import com.sem.kingapputils.utils.AppRunUtils;
import com.sem.kingapputils.utils.BarUtils;
import com.sem.kingapputils.utils.ScreenUtils;
import com.sem.kingapputils.utils.Utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * @ProjectName: VQCStation
 * @Package: com.sem.kingapputils.ui.mvvm.activity
 * @ClassName: KmBaseActivity
 * @Description: 可以正常继承使用
 * @Author: king
 * @CreateDate: 2021/5/8 16:19
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/8 16:19
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class KmBaseActivity extends KDataBindingActivity {

    private ViewModelProvider mActivityProvider;
    private ViewModelProvider mApplicationProvider;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        QMUIStatusBarHelper.translucent(this);
        getLifecycle().addObserver(NetworkStateManager.getInstance());
        //处理错误
        errorHandler();
        observer();
        //TODO tip 1: DataBinding 严格模式（详见 DataBindingActivity - - - - - ）：
        // 将 DataBinding 实例限制于 base 页面中，默认不向子类暴露，
        // 通过这样的方式，来彻底解决 视图调用的一致性问题，
        // 如此，视图调用的安全性将和基于函数式编程思想的 Jetpack Compose 持平。

        // 如果这样说还不理解的话，详见 https://xiaozhuanlan.com/topic/9816742350 和 https://xiaozhuanlan.com/topic/2356748910
    }

    protected abstract void observer();

    protected <T extends ViewModel> T getActivityScopeViewModel(@NonNull Class<T> modelClass) {
        if (mActivityProvider == null) {
            mActivityProvider = new ViewModelProvider(this);
        }
        return mActivityProvider.get(modelClass);
    }

    protected <T extends ViewModel> T getApplicationScopeViewModel(@NonNull Class<T> modelClass) {
        if (mApplicationProvider == null) {
            mApplicationProvider = new ViewModelProvider((BaseApplication) this.getApplicationContext(),
                    getAppFactory(this));
        }
        return mApplicationProvider.get(modelClass);
    }

    private ViewModelProvider.Factory getAppFactory(Activity activity) {
        Application application = checkApplication(activity);
        return ViewModelProvider.AndroidViewModelFactory.getInstance(application);
    }

    private Application checkApplication(Activity activity) {
        Application application = activity.getApplication();
        if (application == null) {
            throw new IllegalStateException("Your activity/fragment is not yet attached to "
                    + "Application. You can't request ViewModel before onCreate call.");
        }
        return application;
    }

    @Override
    public Resources getResources() {
        if (ScreenUtils.isPortrait()) {
            return AdaptScreenUtils.adaptWidth(super.getResources(), 360);
        } else {
            return AdaptScreenUtils.adaptHeight(super.getResources(), 640);
        }
    }

    protected void toggleSoftInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    protected void openUrlInBrowser(String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    protected void showLongToast(String text) {
        ToastUtils.show(text);
    }

    protected void showShortToast(String text) {
        ToastUtils.show(text);
    }

    protected void showLongToast(int stringRes) {
        showLongToast(getApplicationContext().getString(stringRes));
    }

    protected void showShortToast(int stringRes) {
        showShortToast(getApplicationContext().getString(stringRes));
    }

    /**
     * 处理查询失败，可以重写特殊处理
     */
    public void errorHandler(){
        getDataBindingConfig().getStateViewModel().errorLiveData.observe(this, (error)->{
            ToastUtils.show(error.getErrorMessage());
            //失败
            doFail();
        });
    }

    /**
     * 失败之后的处理
     */
    protected abstract void doFail();

}
