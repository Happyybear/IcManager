package com.sem.kingapputils.ui.view.bindingadapter;

import android.view.View;

import com.qmuiteam.qmui.widget.QMUITopBar;
import com.qmuiteam.qmui.widget.QMUITopBarLayout;
import com.sem.kingapputils.ui.view.bingding_recyclerview.BaseDataBindingAdapter;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ProjectName: VQCStation
 * @Package: com.sem.kingapputils.ui.view.bindingadapter
 * @ClassName: BindingAdapterQmuiTopView
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2021/5/27 16:04
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/27 16:04
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BindingAdapterQmuiTopBarLayout {

    @BindingAdapter(value = {"topBarTitle"}, requireAll = false)
    public static void setTitle(QMUITopBarLayout topbar, String title) {
        topbar.setTitle(title);
    }

    @BindingAdapter(value = {"addLeftButtonClick"}, requireAll = false)
    public static void addLeftButtonClick(QMUITopBarLayout topBarLayout, View.OnClickListener clickListener) {
        if (topBarLayout != null && clickListener != null) {
            topBarLayout.addLeftBackImageButton().setOnClickListener(clickListener);
        }
    }

    @BindingAdapter(value = {"addLeftButtonClick"}, requireAll = false)
    public static void addLeftButtonClick(QMUITopBar topBarLayout, View.OnClickListener clickListener) {
        if (topBarLayout != null && clickListener != null) {
            topBarLayout.addLeftBackImageButton().setOnClickListener(clickListener);
        }
    }

    @BindingAdapter(value = {"topBarTitle"}, requireAll = false)
    public static void setTitle(QMUITopBar topbar, String title) {
        topbar.setTitle(title);
    }
}
