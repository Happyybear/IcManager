package com.sem.kingapputils.ui.view.bingding_recyclerview;

import java.util.List;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ProjectName: VQCStation
 * @Package: com.sem.kingapputils.ui.view.bingding_recyclerview
 * @ClassName: RecyclerViewBindingAdapter
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2021/5/19 14:39
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/19 14:39
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RecyclerViewBindingAdapter {

    @BindingAdapter(value = {"adapter"}, requireAll = false)
    public static void setAdapter(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(null);
    }

//    @BindingAdapter(value = {"adapter","onItemClickListener"}, requireAll = false)
//    public static void setOnItemClickListener(RecyclerView recyclerView,RecyclerView.Adapter adapter, BaseDataBindingAdapter.OnItemClickListener clickListener) {
//        recyclerView.setAdapter(adapter);
//        recyclerView.setItemAnimator(null);
//        if (adapter instanceof BaseDataBindingAdapter) {
//            ((BaseDataBindingAdapter)adapter).setOnItemClickListener(clickListener);
//        }
//    }

    @BindingAdapter(value = {"submitList"}, requireAll = false)
    public static void submitList(RecyclerView recyclerView, List list) {
        if (recyclerView.getAdapter() != null) {
            ListAdapter adapter = (ListAdapter) recyclerView.getAdapter();
            adapter.submitList(list);
        }
    }

    @BindingAdapter(value = {"customDecoration"}, requireAll = false)
    public static void setDecoration(RecyclerView recyclerView, RecyclerView.ItemDecoration decoration) {
        if (decoration != null) {
            recyclerView.addItemDecoration(decoration);
        }
    }

    @BindingAdapter(value = {"notifyCurrentListChanged"}, requireAll = false)
    public static void notifyCurrentListChanged(RecyclerView recyclerView, boolean notifyCurrentListChanged) {
        if (notifyCurrentListChanged) {
            recyclerView.getAdapter().notifyDataSetChanged();
        }
    }

    @BindingAdapter(value = {"autoScrollToTopWhenInsert", "autoScrollToBottomWhenInsert"}, requireAll = false)
    public static void autoScroll(RecyclerView recyclerView,
                                  boolean autoScrollToTopWhenInsert,
                                  boolean autoScrollToBottomWhenInsert) {

        if (recyclerView.getAdapter() != null) {
            recyclerView.getAdapter().registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
                @Override
                public void onItemRangeInserted(int positionStart, int itemCount) {
                    if (autoScrollToTopWhenInsert) {
                        recyclerView.scrollToPosition(0);
                    } else if (autoScrollToBottomWhenInsert) {
                        recyclerView.scrollToPosition(recyclerView.getAdapter().getItemCount());
                    }
                }
            });
        }
    }


}
