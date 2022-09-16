package com.sem.kingapputils.ui.view.bingding_recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ProjectName: VQCStation
 * @Package: com.sem.kingapputils.ui.view.bingding_recyclerview
 * @ClassName: BaseBindingAdapter
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2021/5/14 15:02
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/14 15:02
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class BaseDataBindingAdapter<M, B extends ViewDataBinding> extends ListAdapter<M, RecyclerView.ViewHolder> {

    protected Context mContext;

    protected OnItemClickListener<M> mOnItemClickListener;
    protected OnItemLongClickListener<M> mOnItemLongClickListener;

    @BindingAdapter(value = {"onItemClickListener"}, requireAll = false)
    public static void setOnItemClickListener(RecyclerView recyclerView, OnItemClickListener clickListener) {
        if (recyclerView.getAdapter() != null && recyclerView.getAdapter() instanceof BaseDataBindingAdapter) {
            BaseDataBindingAdapter adapter = (BaseDataBindingAdapter) recyclerView.getAdapter();
            adapter.setOnItemClickListener(clickListener);
        }
    }

    public void setOnItemClickListener(OnItemClickListener<M> onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener<M> onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    public BaseDataBindingAdapter(Context context, @NonNull DiffUtil.ItemCallback<M> diffCallback) {
        super(diffCallback);
        this.mContext = context;
    }

    @Override
    public void submitList(@Nullable List<M> list) {
        super.submitList(list, () -> {
            super.submitList(list == null ? new ArrayList<>() : new ArrayList<>(list));
        });
    }

    @Override
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        B binding = DataBindingUtil.inflate(LayoutInflater.from(this.mContext), this.getLayoutResId(viewType), parent, false);
        BaseBindingViewHolder holder = new BaseBindingViewHolder(binding.getRoot());
        holder.itemView.setOnClickListener(v -> {
            if (mOnItemClickListener != null) {
                int position = holder.getBindingAdapterPosition();
                if (position < getItemCount() || position >= 0) {
                    mOnItemClickListener.onItemClick(holder.itemView.getId(), getItem(position), position);
                }
            }
        });
        holder.itemView.setOnLongClickListener(v -> {
            if (mOnItemLongClickListener != null) {
                int position = holder.getBindingAdapterPosition();
                mOnItemLongClickListener.onItemLongClick(holder.itemView.getId(), getItem(position), position);
                return true;
            }
            return false;
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        B binding = DataBindingUtil.getBinding(holder.itemView);
        this.onBindItem(binding, getItem(position), holder);
        if (binding != null) {
            binding.executePendingBindings();
        }
    }

    protected abstract @LayoutRes
    int getLayoutResId(int viewType);

    /**
     * 注意：
     * RecyclerView 中的数据有位置改变（比如删除）时一般不会重新调用 onBindViewHolder() 方法，除非这个元素不可用。
     * 为了实时获取元素的位置，我们通过 ViewHolder.getBindingAdapterPosition() 方法。
     *
     * @param binding .
     * @param item    .
     * @param holder  .
     */
    protected abstract void onBindItem(B binding, M item, RecyclerView.ViewHolder holder);

    public static class BaseBindingViewHolder extends RecyclerView.ViewHolder {
        BaseBindingViewHolder(View itemView) {
            super(itemView);
        }
    }

    public interface OnItemClickListener<M> {
        void onItemClick(int viewId, M item, int position);
    }

    public interface OnItemLongClickListener<M> {
        void onItemLongClick(int viewId, M item, int position);
    }
}
