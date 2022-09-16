package com.sem.kingapputils.ui.base.config;

import android.util.SparseArray;

import com.sem.kingapputils.ui.base.viewmodel.BaseViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

/**
 * @ProjectName: VQCStation
 * @Package: com.sem.kingapputils.ui.mvvm.config
 * @ClassName: DataBindingConfig
 * @Description: java类作用描述
 *  * 将 DataBinding 实例限制于 base 页面中，默认不向子类暴露，
 *  * 通过这样的方式，来彻底解决 视图调用的一致性问题，
 *  * 如此，视图实例的安全性将和基于函数式编程思想的 Jetpack Compose 持平。
 *  * 而 DataBindingConfig 就是在这样的背景下，用于为 base 页面中的 DataBinding 提供绑定项。
 * @Author: king
 * @CreateDate: 2021/5/8 15:52
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/8 15:52
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class DataBindingConfig {

    private final int layout;

    private final int vmVariableId;

    private final BaseViewModel stateViewModel;

    private SparseArray bindingParams = new SparseArray();

    public DataBindingConfig(@NonNull Integer layout,
                             @NonNull Integer vmVariableId,
                             @NonNull BaseViewModel stateViewModel) {
        this.layout = layout;
        this.vmVariableId = vmVariableId;
        this.stateViewModel = stateViewModel;
    }

    public int getLayout() {
        return layout;
    }

    public int getVmVariableId() {
        return vmVariableId;
    }

    public BaseViewModel getStateViewModel() {
        return stateViewModel;
    }

    public SparseArray getBindingParams() {
        return bindingParams;
    }

    public DataBindingConfig addBindingParam(@NonNull Integer variableId,
                                             @NonNull Object object) {
        if (bindingParams.get(variableId) == null) {
            bindingParams.put(variableId, object);
        }
        return this;
    }
}
