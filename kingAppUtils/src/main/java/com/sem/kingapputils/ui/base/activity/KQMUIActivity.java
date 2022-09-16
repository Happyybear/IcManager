package com.sem.kingapputils.ui.base.activity;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;

import com.qmuiteam.qmui.arch.QMUIActivity;
import com.sem.kingapputils.utils.AppInfoManager;
import com.sem.kingapputils.utils.KDisplayUtils;

import androidx.annotation.Nullable;


/**
 * @ProjectName: VQCStation
 * @Package: com.sem.kingapputils.ui.base.activity
 * @ClassName: KQMUIActivity
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2021/5/20 11:03
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/20 11:03
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class KQMUIActivity extends QMUIActivity {

//    @Override
//    public void setContentView(View view) {
//        //为了使用view binding，不调用基类setContentView(view)方法
//        getDelegate().setContentView(view);
//    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        fontScale = AppInfoManager.getsInstance().getFontSizeScale();
        super.onCreate(savedInstanceState);
    }

    @Override
    public Resources getResources() {
        Resources resources = super.getResources();
        return KDisplayUtils.getResources(this, resources, AppInfoManager.getsInstance().getFontSizeScale());
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(KDisplayUtils.attachBaseContext(newBase, AppInfoManager.getsInstance().getFontSizeScale()));
    }

    /**
     * 设置字体大小，同时通知界面重绘
     */
    public void setFontScale(float fontScale) {
        AppInfoManager.getsInstance().setFontSizeScale(fontScale);;
        KDisplayUtils.recreate(this);
    }
//
//    @Override
//    public void setContentView(int layoutResID) {
//        getDelegate().setContentView(layoutResID);
//    }
//
//    @Override
//    public void setContentView(View view, ViewGroup.LayoutParams params) {
//        getDelegate().setContentView(view, params);
//    }
}
