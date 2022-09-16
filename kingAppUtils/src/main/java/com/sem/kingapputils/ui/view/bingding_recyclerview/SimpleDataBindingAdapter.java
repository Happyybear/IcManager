package com.sem.kingapputils.ui.view.bingding_recyclerview;

import android.content.Context;

import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ProjectName: VQCStation
 * @Package: com.sem.kingapputils.ui.bindingAdapter
 * @ClassName: SimpleDataBindingAdapter
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2021/5/19 13:28
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/19 13:28
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class SimpleDataBindingAdapter<M, B extends ViewDataBinding> extends BaseDataBindingAdapter<M, B> {

    private final int layout;


    public SimpleDataBindingAdapter(Context context, int layout, @NonNull DiffUtil.ItemCallback<M> diffCallback) {
        super(context, diffCallback);
        this.layout = layout;
    }

    @Override
    protected @LayoutRes
    int getLayoutResId(int viewType) {
        return this.layout;
    }

}
