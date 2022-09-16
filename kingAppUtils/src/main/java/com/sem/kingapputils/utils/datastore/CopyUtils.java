package com.sem.kingapputils.utils.datastore;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * @ProjectName: sem
 * @Package: com.sem.kingapputils.utils.datastore
 * @ClassName: CopyUtils
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2022/7/25 10:18
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/7/25 10:18
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CopyUtils {
    public static<T> T multiCopy(T obj){
        Gson gson = new Gson();
        try {
            return gson.fromJson(gson.toJson(obj), (Type) obj.getClass());
        }catch (Exception e){
            return obj;
        }
    }
}
