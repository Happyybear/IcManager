package com.sem.kingapputils.utils.datastore;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ProjectName: VQCStation
 * @Package: com.sem.kingapputils.utils
 * @ClassName: JsonFileUtils
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2021/5/28 19:33
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/28 19:33
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class JsonFileUtils {

    public static <T> List<T> parseAssertDataGson(Context context, String name, Class<T[]> cls) {
        InputStream is = null;
        try {
            is = context.getAssets().open(name, Context.MODE_PRIVATE);
            int length = is.available();
            byte[] buffer = new byte[length];
            is.read(buffer);
            Reader response = new StringReader(new String(buffer));
            Gson gson = new Gson();
//            List<T> mydata = gson.fromJson(response, new TypeToken<List<T>>(){}.getType());
            T[] arr = gson.fromJson(response, cls);
            return Arrays.asList(arr);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
