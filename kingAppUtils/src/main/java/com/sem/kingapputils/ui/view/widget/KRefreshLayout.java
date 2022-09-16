package com.sem.kingapputils.ui.view.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.sem.kingapputils.R;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;

/**
 * @ProjectName: sem
 * @Package: com.sem.kingapputils.ui.view.widget
 * @ClassName: KRefreshLayout
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2021/10/12 9:48
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/10/12 9:48
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class KRefreshLayout extends SmartRefreshLayout {

    public KRefreshLayout(Context context) {
        super(context);
        configTheme();
    }

    public KRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        configTheme();
    }

    private void configTheme(){
//        setPrimaryColorsId(R.color.color_content_background);//全局设置主题颜色
    }


    public void autoRefreshK(){
        autoRefresh(400);
    }

    public void setOnKRefreshListener(@NonNull OnRefreshListener refreshListener){
        setOnRefreshListener(refreshListener);
    }

    public void finishKRefresh(boolean b) {
        finishRefresh(b);
    }

    public void finishKRefresh() {
        finishRefresh();
    }



    /********* BindingAdapter*************/
    @BindingAdapter(value = {"state"}, requireAll = false)
    public static void setKRefreshState(KRefreshLayout pullRefreshLayout, Boolean state) {
        if (state == null){
            return;
        }
        if (state){
            pullRefreshLayout.autoRefreshK();
        }else {
            pullRefreshLayout.finishKRefresh();
        }
    }

    @BindingAdapter(value = {"refreshStrike"}, requireAll = false)
    public static void setKRefreshStrike(KRefreshLayout pullRefreshLayout, KPullRefreshStrike refreshStrike) {
        pullRefreshLayout.setOnKRefreshListener(refreshLayout -> {
            if (refreshStrike != null){
                refreshStrike.onRefresh();
            }
        });
    }

    @BindingAdapter(value = {"kPrimaryColorsId"}, requireAll = false)
    public static void setKPrimaryColorsId(KRefreshLayout pullRefreshLayout, @ColorRes int... colors) {
        pullRefreshLayout.setOnKRefreshListener(refreshLayout -> {
            if (colors != null){
                pullRefreshLayout.setPrimaryColorsId(colors);
            }
        });
    }

    public interface KPullRefreshStrike{
        void onRefresh();
    }
}
