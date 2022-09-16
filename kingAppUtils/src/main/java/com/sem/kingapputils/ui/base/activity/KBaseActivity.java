package com.sem.kingapputils.ui.base.activity;

import android.util.Log;

import com.qmuiteam.qmui.arch.QMUIActivity;

import java.io.ObjectOutputStream;

import androidx.appcompat.app.AppCompatActivity;

/**
 * @ProjectName: VQCStation
 * @Package: com.sem.kingapputils.ui.mvvm.activity
 * @ClassName: BaseActivity
 * @Description: 顶级Activity基类，不要直接继承使用
 * @Author: king
 * @CreateDate: 2021/5/8 15:36
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/8 15:36
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class KBaseActivity extends KQMUIActivity {

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("onDestroy",this.getClass().getName());
    }

}
