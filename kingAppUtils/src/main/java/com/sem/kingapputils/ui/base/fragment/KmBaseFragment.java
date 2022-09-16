package com.sem.kingapputils.ui.base.fragment;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.hjq.toast.ToastUtils;
import com.qmuiteam.qmui.arch.QMUIFragment;
import com.sem.kingapputils.BaseApplication;
import com.sem.kingapputils.ui.base.viewmodel.BaseViewModel;
import com.sem.kingapputils.utils.KArrayUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import static com.sem.kingapputils.ui.view.bindingemptyview.EmptyViewBindingAdapter.EMPTY_VIEW_FAIL;
import static com.sem.kingapputils.ui.view.bindingemptyview.EmptyViewBindingAdapter.EMPTY_VIEW_HIDDEN;

/**
 * @ProjectName: VQCStation
 * @Package: com.sem.kingapputils.ui.base.fragment
 * @ClassName: KmBaseFragment
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2021/5/12 9:40
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/12 9:40
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class KmBaseFragment extends KDataBindingFragment {
    private ViewModelProvider mFragmentProvider;
    private ViewModelProvider mActivityProvider;
    private ViewModelProvider mApplicationProvider;

    private static final Handler HANDLER = new Handler();
    protected boolean mAnimationLoaded;

    //TODO tip 1: DataBinding 严格模式（详见 DataBindingFragment - - - - - ）：
    // 将 DataBinding 实例限制于 base 页面中，默认不向子类暴露，
    // 通过这样的方式，来彻底解决 视图调用的一致性问题，
    // 如此，视图调用的安全性将和基于函数式编程思想的 Jetpack Compose 持平。

    // 如果这样说还不理解的话，详见 https://xiaozhuanlan.com/topic/9816742350 和 https://xiaozhuanlan.com/topic/2356748910

    //TODO tip 2: Jetpack 通过 "工厂模式" 来实现 ViewModel 的作用域可控，
    //目前我们在项目中提供了 Application、Activity、Fragment 三个级别的作用域，
    //值得注意的是，通过不同作用域的 Provider 获得的 ViewModel 实例不是同一个，
    //所以如果 ViewModel 对状态信息的保留不符合预期，可以从这个角度出发去排查 是否眼前的 ViewModel 实例不是目标实例所致。

    //如果这样说还不理解的话，详见 https://xiaozhuanlan.com/topic/6257931840

    protected <T extends ViewModel> T getFragmentScopeViewModel(@NonNull Class<T> modelClass) {
        if (mFragmentProvider == null) {
            mFragmentProvider = new ViewModelProvider(this);
        }
        return mFragmentProvider.get(modelClass);
    }

    protected <T extends ViewModel> T getActivityScopeViewModel(@NonNull Class<T> modelClass) {
        if (mActivityProvider == null) {
            mActivityProvider = new ViewModelProvider(mActivity);
        }
        return mActivityProvider.get(modelClass);
    }

    protected <T extends ViewModel> T getApplicationScopeViewModel(@NonNull Class<T> modelClass) {
        if (mApplicationProvider == null) {
            mApplicationProvider = new ViewModelProvider(
                    (BaseApplication) mActivity.getApplicationContext(), getApplicationFactory(mActivity));
        }
        return mApplicationProvider.get(modelClass);
    }

    private ViewModelProvider.Factory getApplicationFactory(Activity activity) {
        checkActivity(this);
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

    private void checkActivity(Fragment fragment) {
        Activity activity = fragment.getActivity();
        if (activity == null) {
            throw new IllegalStateException("Can't create ViewModelProvider for detached fragment");
        }
    }

    protected NavController nav() {
        return NavHostFragment.findNavController(this);
    }

    protected void safeNavigate(int navId, int homeId){
        if (nav().getCurrentDestination() != null) {
            if (nav().getCurrentDestination().getId() != homeId) return;
            nav().navigate(navId);
        }
    }
    @Nullable
    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        //TODO 错开动画转场与 UI 刷新的时机，避免掉帧卡顿的现象
        HANDLER.postDelayed(() -> {
            if (!mAnimationLoaded) {
                mAnimationLoaded = true;
                loadInitData();
            }
        }, 280);
        return super.onCreateAnimation(transit, enter, nextAnim);
    }

    protected void loadInitData() {

    }

    protected void toggleSoftInput() {
        InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Activity.INPUT_METHOD_SERVICE);
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
        showLongToast(mActivity.getApplicationContext().getString(stringRes));
    }

    protected void showShortToast(int stringRes) {
        showShortToast(mActivity.getApplicationContext().getString(stringRes));
    }

    /**
     * [页面跳转]
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        startActivity(clz, null);
    }

    /**
     * [携带数据的页面跳转]
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(mActivity, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initCustomView();
        errorHandler();
        observer();
        doBusiness();
    }

    public void doBusiness() {
    }

    /**
     * 处理查询失败，可以重写特殊处理
     */
    public void errorHandler(){
        getDataBindingConfig().getStateViewModel().errorLiveData.observe(getViewLifecycleOwner(), (error)->{
            ToastUtils.show(error.getErrorMessage());
            doFail();
        });
    }
    /**
     * 失败之后的处理
     */
    protected abstract void doFail();

    protected abstract void observer();

    protected abstract void initCustomView();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        BaseViewModel stateViewModel = getDataBindingConfig().getStateViewModel();
        if (stateViewModel != null){
            stateViewModel.stopNet();
        }
    }
}
