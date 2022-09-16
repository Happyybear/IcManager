package com.sem.kingapputils.ui.base.activity;

import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sem.kingapputils.R;
import com.sem.kingapputils.ui.base.config.DataBindingConfig;
import com.sem.kingapputils.utils.AppRunUtils;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * @ProjectName: VQCStation
 * @Package: com.sem.kingapputils.ui.mvvm.activity
 * @ClassName: KDataBindingActivity
 * @Description: BindingActivity
 * @Author: king
 * @CreateDate: 2021/5/8 15:38
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/8 15:38
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class KDataBindingActivity extends KBaseActivity {
    private ViewDataBinding mBinding;
    private TextView mTvStrictModeTip;

    protected String TAG;

    /**
     * 初始化VM
     */
    protected abstract void initViewModel();

    /**
     * 获取DBingding配置
     * @return DataBindingConfig
     */
    protected abstract DataBindingConfig getDataBindingConfig();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (AppRunUtils.checkAppResourceRecycle()){
            // 重启App
            AppRunUtils.reStart(this);
        }
        TAG = getClass().getName();
        initViewModel();
        DataBindingConfig dataBindingConfig = getDataBindingConfig();

        //TODO tip: DataBinding 严格模式：
        // 将 DataBinding 实例限制于 base 页面中，默认不向子类暴露，
        // 通过这样的方式，来彻底解决 视图调用的一致性问题，
        // 如此，视图调用的安全性将和基于函数式编程思想的 Jetpack Compose 持平。

        // 由于QMUIActivity增加了手势返回，会比系统多一层 ViewsetContentView会报错
        ViewDataBinding binding = DataBindingUtil.inflate(getLayoutInflater(),  dataBindingConfig.getLayout(), null, false);
        setContentView(binding.getRoot());
//        ViewDataBinding binding = DataBindingUtil.setContentView(this, dataBindingConfig.getLayout());
        binding.setLifecycleOwner(this);
        binding.setVariable(dataBindingConfig.getVmVariableId(), dataBindingConfig.getStateViewModel());
        SparseArray bindingParams = dataBindingConfig.getBindingParams();
        for (int i = 0, length = bindingParams.size(); i < length; i++) {
            binding.setVariable(bindingParams.keyAt(i), bindingParams.valueAt(i));
        }
        mBinding = binding;
    }


    public boolean isDebug() {
        return getApplicationContext().getApplicationInfo() != null &&
                (getApplicationContext().getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }

    /**
     * TODO tip: 警惕使用。非必要情况下，尽可能不在子类中拿到 binding 实例乃至获取 view 实例。使用即埋下隐患。
     * 目前的方案是在 debug 模式下，对获取实例的情况给予提示。
     * <p>
     * 如果这样说还不理解的话，详见 https://xiaozhuanlan.com/topic/9816742350 和 https://xiaozhuanlan.com/topic/2356748910
     *
     * @return binding
     */
    protected ViewDataBinding getBinding() {
        if (isDebug() && mBinding != null) {
//            if (mTvStrictModeTip == null) {
//                mTvStrictModeTip = new TextView(getApplicationContext());
//                mTvStrictModeTip.setAlpha(0.4f);
//                mTvStrictModeTip.setTextSize(14);
//                mTvStrictModeTip.setBackgroundColor(Color.WHITE);
//                mTvStrictModeTip.setText(R.string.debug_activity_databinding_warning);
//                ((ViewGroup) mBinding.getRoot()).addView(mTvStrictModeTip);
//            }
        }
        return mBinding;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBinding.unbind();
        mBinding = null;
    }
}
