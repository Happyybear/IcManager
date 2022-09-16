package com.sem.kingapputils.utils;

import android.os.Handler;
import android.os.Looper;

/**
 * @ProjectName: VQCStation
 * @Package: com.sem.kingapputils.utils
 * @ClassName: SafeHandler
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2021/5/19 15:56
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/5/19 15:56
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class SafeHandler extends Handler{
   private static volatile SafeHandler mInstance;

   private SafeHandler(){
       super(Looper.getMainLooper());
   }

   public static SafeHandler getInstance(){
       if (mInstance == null){
           synchronized (SafeHandler.class){
               if (mInstance == null){
                   mInstance = new SafeHandler();
               }
           }
       }
       return mInstance;
   }
}
