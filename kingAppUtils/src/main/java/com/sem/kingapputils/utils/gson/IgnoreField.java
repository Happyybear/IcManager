package com.sem.kingapputils.utils.gson;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ProjectName: sem
 * @Package: com.sem.kingapputils.utils.gson
 * @ClassName: IgnoreFied
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2022/9/8 10:06
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/9/8 10:06
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreField {
}