package com.sem.kingapputils.ui.view.bindingemptyview;

import android.view.View;

import com.qmuiteam.qmui.widget.QMUIEmptyView;
import com.qmuiteam.qmui.widget.pullRefreshLayout.QMUIPullRefreshLayout;

import androidx.annotation.ColorInt;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ProjectName: VQCStation
 * @Package: com.sem.kingapputils.ui.view.bindingemptyview
 * @ClassName: EmptyViewBindingAdapter
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2021/5/31 18:14
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/31 18:14
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class EmptyViewBindingAdapter {

    public static final int EMPTY_VIEW_HIDDEN = 0;
    public static final int EMPTY_VIEW_LOADING = 1;
    public static final int EMPTY_VIEW_FAIL = 2;
    public static final int EMPTY_VIEW_NO_DATA = 3;
    /**
     * @param emptyView
     * @param state  0 隐藏 1加载中loading 2失败 3无数据
     */
    @BindingAdapter(value = {"state"}, requireAll = false)
    public static void setAdapter(KEmptyView emptyView, int state) {
        switch (state){
            case EMPTY_VIEW_HIDDEN:{
                emptyView.hide();
                emptyView.setClickable(false);
            }
            break;
            case EMPTY_VIEW_LOADING:{
                emptyView.show(true);
                emptyView.setClickable(false);
            }
            break;
            case EMPTY_VIEW_FAIL:{
                emptyView.show("查询失败,点击重试", null);
                emptyView.setClickable(true);
            }
            break;
            case EMPTY_VIEW_NO_DATA:{
                emptyView.show("没有查询到相关信息", null);
                emptyView.setClickable(false);
            }
            break;
            default:
        }
    }

    @BindingAdapter(value = {"tapAction"}, requireAll = false)
    public static void setTapAction(KEmptyView emptyView, View.OnClickListener clickListener) {
        if (clickListener != null){
            emptyView.setOnClickListener(v -> {
                setAdapter(emptyView, EMPTY_VIEW_LOADING);
                clickListener.onClick(v);
            });
        }
    }


    @BindingAdapter(value = {"state"}, requireAll = false)
    public static void setAdapter(QMUIPullRefreshLayout pullRefreshLayout, Boolean state) {
        if (state == null){
            return;
        }
        if (state){
            pullRefreshLayout.setToRefreshDirectly();
        }else {
            pullRefreshLayout.finishRefresh();
        }
    }

    @BindingAdapter(value = {"refreshStrike"}, requireAll = false)
    public static void setAdapter(QMUIPullRefreshLayout pullRefreshLayout, QMUIPullRefreshStrike refreshStrike) {
        pullRefreshLayout.setOnPullListener(new QMUIPullRefreshLayout.OnPullListener() {
            @Override
            public void onMoveTarget(int offset) {

            }

            @Override
            public void onMoveRefreshView(int offset) {

            }

            @Override
            public void onRefresh() {
                if (refreshStrike != null){
                    refreshStrike.onRefresh();
                }
            }
        });
    }

    public interface QMUIPullRefreshStrike{
        void onRefresh();
    }
}
