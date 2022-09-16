package com.sem.kingapputils.ui.toast;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.TypedValue;

import com.hjq.toast.style.BlackToastStyle;

/**
 * @ProjectName: sem
 * @Package: com.sem.kingapputils.ui.toast
 * @ClassName: KToastNormalStyle
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2022/1/4 15:14
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/1/4 15:14
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class KToastNormalStyle extends BlackToastStyle {

    @Override
    protected int getTextColor(Context context) {
        return 0XBBFFFFFF;
    }

    @Override
    protected Drawable getBackgroundDrawable(Context context) {
        GradientDrawable drawable = new GradientDrawable();
        // 设置颜色
        drawable.setColor(0XFF2c89f3);
        // 设置圆角
        drawable.setCornerRadius(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, context.getResources().getDisplayMetrics()));
        return drawable;
    }
}
