package com.sem.kingapputils.ui.base.fragment;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sem.kingapputils.ui.base.config.DataBindingConfig;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

/**
 * @ProjectName: VQCStation
 * @Package: com.sem.kingapputils.ui.base.fragment
 * @ClassName: KDataBindingFragment
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2021/5/12 9:40
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/12 9:40
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class KDataBindingFragment extends KBaseFragment {
    protected AppCompatActivity mActivity;
    private ViewDataBinding mBinding;
    private TextView mTvStrictModeTip;
    private DataBindingConfig  dataBindingConfig;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (AppCompatActivity) context;
    }

    protected abstract void initViewModel();
    protected abstract void prepareData();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
        prepareData();
    }

    protected abstract DataBindingConfig getDataBindingConfig();

    /**
     * TODO tip: 警惕使用。非必要情况下，尽可能不在子类中拿到 binding 实例乃至获取 view 实例。使用即埋下隐患。
     * 目前的方案是在 debug 模式下，对获取实例的情况给予提示。
     * <p>
     * 如果这样说还不理解的话，详见 https://xiaozhuanlan.com/topic/9816742350 和 https://xiaozhuanlan.com/topic/2356748910
     *
     * @return binding
     */
    protected ViewDataBinding getBinding() {
//        if (isDebug() && mBinding != null) {
//            if (mTvStrictModeTip == null) {
//                mTvStrictModeTip = new TextView(getContext());
//                mTvStrictModeTip.setAlpha(0.5f);
//                mTvStrictModeTip.setTextSize(16);
//                mTvStrictModeTip.setBackgroundColor(Color.WHITE);
//                mTvStrictModeTip.setText(R.string.debug_fragment_databinding_warning);
//                ((ViewGroup) mBinding.getRoot()).addView(mTvStrictModeTip);
//            }
//        }
        return mBinding;
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {

        dataBindingConfig = getDataBindingConfig();

        //TODO tip: DataBinding 严格模式：
        // 将 DataBinding 实例限制于 base 页面中，默认不向子类暴露，
        // 通过这样的方式，来彻底解决 视图调用的一致性问题，
        // 如此，视图调用的安全性将和基于函数式编程思想的 Jetpack Compose 持平。

        // 如果这样说还不理解的话，详见 https://xiaozhuanlan.com/topic/9816742350 和 https://xiaozhuanlan.com/topic/2356748910

        ViewDataBinding binding = DataBindingUtil.inflate(inflater, dataBindingConfig.getLayout(), container, false);
        binding.setLifecycleOwner(this);
        binding.setVariable(dataBindingConfig.getVmVariableId(), dataBindingConfig.getStateViewModel());
        SparseArray bindingParams = dataBindingConfig.getBindingParams();
        for (int i = 0, length = bindingParams.size(); i < length; i++) {
            binding.setVariable(bindingParams.keyAt(i), bindingParams.valueAt(i));
        }
        mBinding = binding;
        return binding.getRoot();
    }


    public boolean isDebug() {
        return mActivity.getApplicationContext().getApplicationInfo() != null &&
                (mActivity.getApplicationContext().getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding.unbind();
        mBinding = null;
    }
}
