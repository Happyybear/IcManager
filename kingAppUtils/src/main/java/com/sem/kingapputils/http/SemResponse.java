package com.sem.kingapputils.http;

import per.goweii.rxhttp.request.base.BaseResponse;

/**
 * @author CuiZhen
 * @date 2019/5/8
 * GitHub: https://github.com/goweii
 */
public class SemResponse<E> implements BaseResponse<E> {

    private String resultCode;
    private String errorMsg;
    private E data;

    @Override
    public int getCode() {
        return Integer.parseInt(resultCode);
    }

    @Override
    public void setCode(int code) {
        resultCode = String.valueOf(code);
    }

    @Override
    public E getData() {
        return data;
    }

    @Override
    public void setData(E data) {
        this.data = data;
    }

    @Override
    public String getMsg() {
        return errorMsg;
    }

    @Override
    public void setMsg(String msg) {
        errorMsg = msg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
}
