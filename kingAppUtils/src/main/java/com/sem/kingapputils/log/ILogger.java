package com.sem.kingapputils.log;

/**
 * @ProjectName: sem
 * @Package: com.sem.kingapputils.log
 * @ClassName: ILogger
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2022/7/8 9:25
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/7/8 9:25
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface ILogger {

    /**
     * 打印信息
     *
     * @param priority 优先级
     * @param tag      标签
     * @param message  信息
     * @param t        出错信息
     */
    void log(int priority, String tag, String message, Throwable t);
}
