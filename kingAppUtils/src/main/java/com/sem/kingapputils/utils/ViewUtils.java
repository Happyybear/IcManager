package com.sem.kingapputils.utils;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ItemAnimator;
import androidx.recyclerview.widget.SimpleItemAnimator;
import androidx.viewpager2.widget.ViewPager2;

/**
 * @ProjectName: sem
 * @Package: com.sem.kingapputils.utils
 * @ClassName: ViewUtils
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2022/5/5 17:59
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/5 17:59
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ViewUtils {
    public static void setSupportsChangeAnimations(ViewPager2 viewPager, boolean enable) {

        for (int i = 0; i < viewPager.getChildCount(); i++) {

            View view = viewPager.getChildAt(i);

            if (view instanceof RecyclerView){

                RecyclerView.ItemAnimator animator = ((RecyclerView) view).getItemAnimator();

                if (animator != null) {

                    ((SimpleItemAnimator) animator).setSupportsChangeAnimations(enable);

                }

                break;

            }

        }

    }
}
