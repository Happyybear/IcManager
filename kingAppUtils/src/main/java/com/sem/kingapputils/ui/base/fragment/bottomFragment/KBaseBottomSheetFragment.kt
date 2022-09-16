package com.sem.kingapputils.ui.base.fragment.bottomFragment

import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

/**
 *
 * @ProjectName: VQCStation
 * @Package: com.sem.kingapputils.ui.base.fragment
 * @ClassName: KBaseFragment
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2021/7/5 13:36
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/7/5 13:36
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */

abstract class KBaseBottomSheetFragment: BottomSheetDialogFragment(){
    override fun onDestroy() {
        super.onDestroy()
        Log.d("onDestroy", javaClass.name)
    }
}