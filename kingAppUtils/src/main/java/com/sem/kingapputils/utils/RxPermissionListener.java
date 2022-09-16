package com.sem.kingapputils.utils;

/**
 * @ProjectName: sem
 * @Package: com.sem.kingapputils.utils
 * @ClassName: RxPermissionListener
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2021/10/14 11:00
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/10/14 11:00
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class RxPermissionListener {

    public abstract void accept();

    public abstract void refuse();

    public void noAsk(){

    }
}
