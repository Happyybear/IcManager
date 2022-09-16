package com.sem.kingapputils.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

/**
 * @ProjectName: sem
 * @Package: com.sem.kingapputils.utils
 * @ClassName: AppSettingUtils
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2021/10/14 11:12
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/10/14 11:12
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class AppSettingUtils {
    public static void navToSetting(Activity activity){
        Intent intent = new Intent();
        intent.setAction(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + activity.getPackageName()));
        activity.startActivity(intent);
    }
}
