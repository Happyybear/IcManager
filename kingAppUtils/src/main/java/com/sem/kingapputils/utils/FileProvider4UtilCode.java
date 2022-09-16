package com.sem.kingapputils.utils;

import com.tencent.mmkv.MMKV;

import androidx.core.content.FileProvider;

/**
 * @ProjectName: sem
 * @Package: com.sem.kingapputils.utils
 * @ClassName: FileProvider4UtilCode
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2021/7/30 15:20
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/7/30 15:20
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public final class FileProvider4UtilCode extends FileProvider {

    @Override
    public boolean onCreate() {
        Utils.init(getContext());
        MMKV.initialize(getContext());
        return true;
    }
}
