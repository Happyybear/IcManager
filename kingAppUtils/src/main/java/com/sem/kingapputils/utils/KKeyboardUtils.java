package com.sem.kingapputils.utils;

import android.widget.EditText;

import com.blankj.utilcode.util.KeyboardUtils;

/**
 * @ProjectName: sem
 * @Package: com.sem.kingapputils.utils
 * @ClassName: KeyboardUtils
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2022/5/6 15:53
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/5/6 15:53
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class KKeyboardUtils {
    public static void clearFocus(EditText searchText) {
        if (searchText != null) {
            KeyboardUtils.hideSoftInput(searchText);
            searchText.clearFocus();
        }
    }
}
