package com.sem.kingapputils.http;

import per.goweii.rxhttp.request.Api;


/**
 * @author CuiZhen
 * @date 2019/5/7
 * GitHub: https://github.com/goweii
 */
public class SemApi extends Api {

    public static ApiService api() {
        return api(ApiService.class);
    }

    public static class ApiConfig {
        public static final String BASE_URL = "http://119.163.199.214:6503/";
    }

    public static class ApiCode {
        public static final int ERROR = 1000;

        public static final int SUCCESS = 0;

        public static final int FAILED_NO_CACHE = -9000;  //没有缓存

        public static final int FAILED_NOT_LOGIN = -1001; //请先登录
    }

    public interface ApiService {
    }

}
