package com.sem.kingapputils.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.Log;

import com.qmuiteam.qmui.util.QMUIDisplayHelper;

/**
 * @ProjectName: sem
 * @Package: com.sem.kingapputils.utils
 * @ClassName: DisplayUtil
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2021/12/29 9:03
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/12/29 9:03
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class KDisplayUtils {
    /**
     * 保持字体大小不随系统设置变化（用在界面加载之前）
     * 要重写Activity的attachBaseContext()
     */
    public static Context attachBaseContext(Context context, float fontScale) {
        Configuration config = context.getResources().getConfiguration();
        config.fontScale = fontScale;
        return context.createConfigurationContext(config);
    }

    /**
     * 保持字体大小不随系统设置变化（用在界面加载之前）
     * 要重写Activity的getResources()
     */
    public static Resources getResources(Context context, Resources resources, float fontScale) {
        Configuration config = resources.getConfiguration();
        if(config.fontScale != fontScale) {
            config.fontScale = fontScale;
            return context.createConfigurationContext(config).getResources();
        } else {
            return resources;
        }
    }

    /**
     * 保存字体大小，后通知界面重建，它会触发attachBaseContext，来改变字号
     */
    public static void recreate(Activity activity) {
        //只有这句才有效，其它两句都无效
        activity.recreate();
    }

    /**
     * 单位转换: dp -> px
     *
     * @param dp dp
     * @return px
     */
    public static int dp2Px(int dp){
        return QMUIDisplayHelper.dp2px(Utils.getApp().getApplicationContext(),dp);
    }

    public static int getScreenWidth() {
        return QMUIDisplayHelper.getScreenWidth(Utils.getApp().getApplicationContext());
    }
}
