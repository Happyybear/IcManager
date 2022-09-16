package com.sem.kingapputils.http

/**
 * 用来封装业务错误信息
 *
 * @author zs
 * @date 2020-05-09
 */
open class ApiException(val errorMessage: String, val errorCode: Int) :
    Throwable()
