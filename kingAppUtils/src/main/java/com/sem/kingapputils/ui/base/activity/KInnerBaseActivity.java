package com.sem.kingapputils.ui.base.activity;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import com.qmuiteam.qmui.QMUIConfig;
import com.qmuiteam.qmui.arch.QMUILatestVisit;
import com.qmuiteam.qmui.arch.Utils;
import com.qmuiteam.qmui.arch.annotation.LatestVisitRecord;
import com.qmuiteam.qmui.arch.record.LatestVisitArgumentCollector;
import com.qmuiteam.qmui.arch.record.RecordArgumentEditor;
import com.qmuiteam.qmui.arch.scheme.QMUISchemeHandler;
import com.qmuiteam.qmui.skin.QMUISkinLayoutInflaterFactory;
import com.qmuiteam.qmui.skin.QMUISkinManager;

import java.util.concurrent.atomic.AtomicInteger;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.LayoutInflaterCompat;
import androidx.lifecycle.Lifecycle;

/**
 * @ProjectName: VQCStation
 * @Package: com.sem.kingapputils.ui.base.activity
 * @ClassName: KInnerBaseActivity
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2021/5/20 11:06
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/20 11:06
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class KInnerBaseActivity extends AppCompatActivity implements LatestVisitArgumentCollector {
    private static int NO_REQUESTED_ORIENTATION_SET = -100;
    private static final AtomicInteger sNextRc = new AtomicInteger(1);
    private static int sLatestVisitActivityUUid = -1;
    private boolean mConvertToTranslucentCauseOrientationChanged = false;
    private int mPendingRequestedOrientation = NO_REQUESTED_ORIENTATION_SET;
    private QMUISkinManager mSkinManager;
    private final int mUUid = sNextRc.getAndIncrement();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (useQMUISkinLayoutInflaterFactory()) {
            LayoutInflater layoutInflater = LayoutInflater.from(this);
            LayoutInflaterCompat.setFactory2(layoutInflater,
                    new QMUISkinLayoutInflaterFactory(this, layoutInflater));
        }
        super.onCreate(savedInstanceState);
    }

    void convertToTranslucentCauseOrientationChanged() {
        Utils.convertActivityToTranslucent(this);
        mConvertToTranslucentCauseOrientationChanged = true;
    }

    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        if (mConvertToTranslucentCauseOrientationChanged && (Build.VERSION.SDK_INT == Build.VERSION_CODES.O
                || Build.VERSION.SDK_INT == Build.VERSION_CODES.O_MR1)) {
            Log.i("InnerBaseActivity", "setRequestedOrientation when activity is translucent");
            mPendingRequestedOrientation = requestedOrientation;
        } else {
            super.setRequestedOrientation(requestedOrientation);
        }
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mConvertToTranslucentCauseOrientationChanged) {
            mConvertToTranslucentCauseOrientationChanged = false;
            Utils.convertActivityFromTranslucent(this);
            if (mPendingRequestedOrientation != NO_REQUESTED_ORIENTATION_SET) {
                super.setRequestedOrientation(mPendingRequestedOrientation);
                mPendingRequestedOrientation = NO_REQUESTED_ORIENTATION_SET;
            }
        }
    }

    public QMUISkinManager getSkinManager() {
        return mSkinManager;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mSkinManager != null) {
            mSkinManager.register(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mSkinManager != null) {
            mSkinManager.unRegister(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public final void onLatestVisitArgumentChanged() {
        if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.INITIALIZED)
                && sLatestVisitActivityUUid == mUUid) {
        }
    }

    protected boolean shouldPerformLatestVisitRecord() {
        return true;
    }


    @Override
    public void onCollectLatestVisitArgument(RecordArgumentEditor editor) {

    }

    public void setSkinManager(@Nullable QMUISkinManager skinManager) {
        if (mSkinManager != null) {
            mSkinManager.unRegister(this);
        }
        mSkinManager = skinManager;
        if (skinManager != null) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                skinManager.register(this);
            }
        }
    }

    public final boolean isStartedByScheme() {
        return getIntent().getBooleanExtra(QMUISchemeHandler.ARG_FROM_SCHEME, false);
    }

    protected boolean useQMUISkinLayoutInflaterFactory() {
        return true;
    }
}
