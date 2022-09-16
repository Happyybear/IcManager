package com.sem.kingapputils.utils;

import android.graphics.Color;
import android.graphics.drawable.Drawable;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;

/**
 * @ProjectName: sem
 * @Package: com.sem.kingapputils.utils
 * @ClassName: KResourceUtils
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2021/9/16 18:16
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/9/16 18:16
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class KResourceUtils {
    public static Drawable getDrawable(@DrawableRes int drawable){
        return ContextCompat.getDrawable(Utils.getApp().getApplicationContext(), drawable);
    }

    public static int getColor(@ColorRes int drawable){
        return ContextCompat.getColor(Utils.getApp().getApplicationContext(), drawable);
    }
}
