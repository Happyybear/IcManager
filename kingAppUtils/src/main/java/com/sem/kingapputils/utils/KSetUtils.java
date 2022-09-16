package com.sem.kingapputils.utils;

import java.util.Set;

/**
 * @ProjectName: sem
 * @Package: com.sem.kingapputils.utils
 * @ClassName: KSetUtils
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2022/7/20 18:32
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/7/20 18:32
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class KSetUtils {
    public static <T> boolean isEmpty(Set<T> value) {
        return value == null || value.isEmpty();
    }
}
