package com.sem.kingapputils.ui.base.fragment.bottomFragment;

import android.content.Intent;
import android.util.Log;

import com.qmuiteam.qmui.QMUILog;
import com.qmuiteam.qmui.arch.QMUIFragment;
import com.qmuiteam.qmui.arch.QMUISwipeBackActivityManager;
import com.sem.kingapputils.ui.base.fragment.KmBaseFragment;

import androidx.fragment.app.FragmentManager;

/**
 * @ProjectName: VQCStation
 * @Package: com.sem.kingapputils.ui.base.fragment
 * @ClassName: KmBaseListFragment
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2021/5/31 20:07
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/31 20:07
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class KmBaseListBottomSheetFragment extends KmBaseBottomSheetFragment {

    private static String TAG = "KmBaseListBottomSheetFragment";

    /**
     * pop back
     */
    protected void popBackStack() {
        if (checkPopBack()) {
            mActivity.getSupportFragmentManager().popBackStackImmediate();
        }
    }

    private boolean checkPopBack() {
        if (!isResumed()) {
            return false;
        }
        return checkStateLoss("popBackStack");
    }

    private boolean checkStateLoss(String logName) {
        FragmentManager fragmentManager = mActivity.getSupportFragmentManager();
        if (fragmentManager == null) {
            QMUILog.d(TAG, logName + " can not be invoked because fragmentManager == null");
            return false;
        }
        if (fragmentManager.isStateSaved()) {
            QMUILog.d(TAG, logName + " can not be invoked after onSaveInstanceState");
            return false;
        }
        return true;
    }

}
