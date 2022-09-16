package com.sem.kingapputils.ui.base.repo;

import io.reactivex.disposables.Disposable;

/**
 * @ProjectName: sem
 * @Package: com.beseClass.repository
 * @ClassName: BaseRepository
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2021/7/14 9:02
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/7/14 9:02
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class BaseRepository {

    public BaseRepository() {
    }

    /**
     * 中断请求
     */
    public abstract void stopNet();
}
