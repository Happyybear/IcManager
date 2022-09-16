package com.sem.kingapputils.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.sem.kingapputils.IntentUtils;
import com.sem.kingapputils.log.KLogger;

import java.util.Map;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

/**
 * @ProjectName: sem
 * @Package: com.sem.kingapputils.utils
 * @ClassName: ActivityUtils
 * @Description: java类作用描述
 * @Author: king
 * @CreateDate: 2022/7/8 9:15
 * @UpdateUser: 更新者
 * @UpdateDate: 2022/7/8 9:15
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ActivityUtils {

    private ActivityUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 页面跳转
     *
     * @param intent
     */
    public static boolean startActivity(final Intent intent) {
        if (intent == null) {
            KLogger.e("[startActivity failed]: intent == null");
            return false;
        }
        if ( Utils.getApp().getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
            try {
                Utils.getApp().startActivity(intent);
                return true;
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
                KLogger.e(e);
            }
        } else {
            KLogger.e("[resolveActivity failed]: " + (intent.getComponent() != null ? intent.getComponent().getClassName() : intent.getAction()) + " do not register in manifest");
        }
        return false;
    }

    /**
     * 页面跳转
     *
     * @param intent
     */
    public static boolean startActivity(Context context, final Intent intent) {
        if (intent == null) {
            KLogger.e("[startActivity failed]: intent == null");
            return false;
        }
        if (Utils.getApp().getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
            try {
                context.startActivity(intent);
                return true;
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
                KLogger.e(e);
            }
        } else {
            KLogger.e("[resolveActivity failed]: " + (intent.getComponent() != null ? intent.getComponent().getClassName() : intent.getAction()) + " do not register in manifest");
        }
        return false;
    }

    /**
     * 页面跳转,返回数据
     *
     * @param fromActivity
     * @param intent
     * @param requestCode  请求码
     */
    public static boolean startActivityForResult(final Activity fromActivity, final Intent intent, final int requestCode) {
        if (intent == null) {
            KLogger.e("[startActivity failed]: intent == null");
            return false;
        }
        if (Utils.getApp().getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
            try {
                fromActivity.startActivityForResult(intent, requestCode);
                return true;
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
                KLogger.e(e);
            }
        } else {
            KLogger.e("[resolveActivity failed]: " + (intent.getComponent() != null ? intent.getComponent().getClassName() : intent.getAction()) + " do not register in manifest");
        }
        return false;
    }

    /**
     * 页面跳转[fragment使用]
     *
     * @param intent
     */
    public static boolean startActivity(Fragment fragment, final Intent intent) {
        if (intent == null) {
            KLogger.e("[startActivity failed]: intent == null");
            return false;
        }
        if (Utils.getApp().getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
            try {
                fragment.startActivity(intent);
                return true;
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
                KLogger.e(e);
            }
        } else {
            KLogger.e("[resolveActivity failed]: " + (intent.getComponent() != null ? intent.getComponent().getClassName() : intent.getAction()) + " do not register in manifest");
        }
        return false;
    }

    /**
     * 页面跳转,返回数据[fragment使用]
     *
     * @param fragment
     * @param intent
     * @param requestCode  请求码
     */
    public static boolean startActivityForResult(Fragment fragment, final Intent intent, final int requestCode) {
        if (intent == null) {
            KLogger.e("[startActivity failed]: intent == null");
            return false;
        }
        if (Utils.getApp().getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY) != null) {
            try {
                fragment.startActivityForResult(intent, requestCode);
                return true;
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
                KLogger.e(e);
            }
        } else {
            KLogger.e("[resolveActivity failed]: " + (intent.getComponent() != null ? intent.getComponent().getClassName() : intent.getAction()) + " do not register in manifest");
        }
        return false;
    }
    //=================获取Activity跳转意图===============//

    /**
     * 获取Activity跳转意图
     *
     * @param cls    Activity类
     * @param action 隐式启动的动作
     * @return
     */
    @NonNull
    public static Intent getActivityIntent(final Class<? extends Activity> cls, final String action) {
        return IntentUtils.getIntent(Utils.getApp(), cls, action, true);
    }


    /**
     * 获取Activity跳转显式意图
     *
     * @param cls Activity类
     * @return
     */
    @NonNull
    public static Intent getActivityIntent(final Class<? extends Activity> cls) {
        return IntentUtils.getIntent(Utils.getApp(), cls, null, true);
    }

    /**
     * 获取Activity跳转隐式意图
     *
     * @param action 隐式启动的动作
     * @return
     */
    @NonNull
    public static Intent getActivityIntent(final String action) {
        return IntentUtils.getIntent(Utils.getApp(), null, action, true);
    }

    /**
     * 获取Activity跳转意图
     *
     * @param context
     * @param cls     Activity类
     * @param action  隐式启动的动作
     * @return
     */
    @NonNull
    public static Intent getActivityIntent(final Activity context, final Class<? extends Activity> cls, final String action) {
        return IntentUtils.getIntent(context, cls, action);
    }

    /**
     * 获取Activity跳转意图
     *
     * @param context
     * @param cls     Activity类
     * @return
     */
    @NonNull
    public static Intent getActivityIntent(final Activity context, final Class<? extends Activity> cls) {
        return IntentUtils.getIntent(context, cls, null);
    }

    /**
     * 获取Activity跳转意图
     *
     * @param context
     * @param action  隐式启动的动作
     * @return
     */
    @NonNull
    public static Intent getActivityIntent(final Activity context, final String action) {
        return IntentUtils.getIntent(context, null, action);
    }

    /**
     * 获取Activity跳转意图
     *
     * @param cls   Activity类
     * @param key
     * @param param
     * @return
     */
    public static Intent getActivityIntent(final Class<? extends Activity> cls, final String key, final Object param) {
        Intent intent = getActivityIntent(cls);
        IntentUtils.putExtra(intent, key, param);
        return intent;
    }

    /**
     * 获取Activity跳转意图
     *
     * @param cls Activity类
     * @param map 携带的数据
     * @return
     */
    public static Intent getActivityIntent(final Class<? extends Activity> cls, final Map<String, Object> map) {
        Intent intent = getActivityIntent(cls);
        if (map != null && !map.isEmpty()) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                IntentUtils.putExtra(intent, entry.getKey(), entry.getValue());
            }
        }
        return intent;
    }

    //=====================显式启动==================//

    /**
     * 跳转至Activity页面
     *
     * @param cls Activity类
     */
    public static boolean startActivity(final Class<? extends Activity> cls) {
        Intent intent = getActivityIntent(cls);
        return startActivity(intent);
    }

    /**
     * 跳转至Activity页面
     *
     * @param from        Activity类
     * @param to          Activity类
     * @param requestCode 请求码
     */
    public static boolean startActivityForResult(final Activity from, final Class<? extends Activity> to, final int requestCode) {
        Intent intent = getActivityIntent(from, to);
        return startActivityForResult(from, intent, requestCode);
    }


    /**
     * 跳转至Activity页面（单数据）
     *
     * @param cls   Activity类
     * @param key
     * @param param
     */
    public static boolean startActivity(final Class<? extends Activity> cls, final String key, final Object param) {
        return startActivity(getActivityIntent(cls, key, param));
    }

    /**
     * 跳转至Activity页面
     *
     * @param from        Activity类
     * @param to          Activity类
     * @param requestCode 请求码
     * @param key
     * @param param
     */
    public static boolean startActivityForResult(final Activity from, final Class<? extends Activity> to, final int requestCode, final String key, final Object param) {
        Intent intent = getActivityIntent(from, to);
        IntentUtils.putExtra(intent, key, param);
        return startActivityForResult(from, intent, requestCode);
    }


    /**
     * 跳转至Activity页面
     *
     * @param cls Activity类
     * @param map 携带的数据
     */
    public static boolean startActivity(final Class<? extends Activity> cls, final Map<String, Object> map) {
        Intent intent = getActivityIntent(cls, map);
        return startActivity(intent);
    }

    /**
     * 跳转至Activity页面
     *
     * @param from        Activity类
     * @param to          Activity类
     * @param requestCode 请求码
     * @param map         携带的数据
     */
    public static boolean startActivityForResult(final Activity from, final Class<? extends Activity> to, final int requestCode, final Map<String, Object> map) {
        Intent intent = getActivityIntent(from, to);
        if (map != null && !map.isEmpty()) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                IntentUtils.putExtra(intent, entry.getKey(), entry.getValue());
            }
        }
        return startActivityForResult(from, intent, requestCode);
    }

    /**
     * 跳转至Activity页面（单数据）
     *
     * @param cls   Activity类
     * @param key
     * @param param
     */
    public static boolean startActivityWithBundle(final Class<? extends Activity> cls, final String key, final Object param) {
        Intent intent = getActivityIntent(cls);
        Bundle bundle = new Bundle();
        IntentUtils.putBundle(bundle, key, param);
        intent.putExtras(bundle);
        return startActivity(intent);
    }

    /**
     * 跳转至Activity页面
     *
     * @param from        Activity类
     * @param to          Activity类
     * @param requestCode 请求码
     * @param key
     * @param param
     */
    public static boolean startActivityForResultWithBundle(final Activity from, final Class<? extends Activity> to, final int requestCode, final String key, final Object param) {
        Intent intent = getActivityIntent(from, to);
        Bundle bundle = new Bundle();
        IntentUtils.putBundle(bundle, key, param);
        intent.putExtras(bundle);
        return startActivityForResult(from, intent, requestCode);
    }

    /**
     * 跳转至Activity页面
     *
     * @param cls Activity类
     * @param map 携带的数据
     */
    public static boolean startActivityWithBundle(final Class<? extends Activity> cls, final Map<String, Object> map) {
        Intent intent = getActivityIntent(cls);
        if (map != null && !map.isEmpty()) {
            Bundle bundle = new Bundle();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                IntentUtils.putBundle(bundle, entry.getKey(), entry.getValue());
            }
            intent.putExtras(bundle);
        }
        return startActivity(intent);
    }

    /**
     * 跳转至Activity页面
     *
     * @param from        Activity类
     * @param to          Activity类
     * @param requestCode 请求码
     * @param map         携带的数据
     */
    public static boolean startActivityForResultWithBundle(final Activity from, final Class<? extends Activity> to, final int requestCode, final Map<String, Object> map) {
        Intent intent = getActivityIntent(from, to);
        if (map != null && !map.isEmpty()) {
            Bundle bundle = new Bundle();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                IntentUtils.putBundle(bundle, entry.getKey(), entry.getValue());
            }
            intent.putExtras(bundle);
        }
        return startActivityForResult(from, intent, requestCode);
    }


    //=====================隐式启动==================//


    /**
     * 跳转至Activity页面
     *
     * @param action 隐式启动的动作
     */
    public static boolean startActivity(final String action) {
        return startActivity(getActivityIntent(action));
    }

    /**
     * 跳转至Activity页面
     *
     * @param from        Activity类
     * @param action      隐式启动的动作
     * @param requestCode 请求码
     */
    public static boolean startActivityForResult(final Activity from, final String action, final int requestCode) {
        return startActivityForResult(from, getActivityIntent(from, action), requestCode);
    }

    /**
     * 跳转至Activity页面
     *
     * @param action 隐式启动的动作
     * @param key
     * @param param
     */
    public static boolean startActivity(final String action, final String key, final Object param) {
        Intent intent = getActivityIntent(action);
        IntentUtils.putExtra(intent, key, param);
        return startActivity(intent);
    }

    /**
     * 跳转至Activity页面
     *
     * @param from        Activity类
     * @param action      隐式启动的动作
     * @param requestCode 请求码
     * @param key
     * @param param
     */
    public static boolean startActivityForResult(final Activity from, final String action, final int requestCode, final String key, final Object param) {
        Intent intent = getActivityIntent(from, action);
        IntentUtils.putExtra(intent, key, param);
        return startActivityForResult(from, intent, requestCode);
    }

    /**
     * 跳转至Activity页面
     *
     * @param action 隐式启动的动作
     * @param map    携带的数据
     */
    public static boolean startActivity(final String action, final Map<String, Object> map) {
        Intent intent = getActivityIntent(action);
        if (map != null && !map.isEmpty()) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                IntentUtils.putExtra(intent, entry.getKey(), entry.getValue());
            }
        }
        return startActivity(intent);
    }

    /**
     * 跳转至Activity页面
     *
     * @param from        Activity类
     * @param action      隐式启动的动作
     * @param requestCode 请求码
     * @param map         携带的数据
     */
    public static boolean startActivityForResult(final Activity from, final String action, final int requestCode, final Map<String, Object> map) {
        Intent intent = getActivityIntent(from, action);
        if (map != null && !map.isEmpty()) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                IntentUtils.putExtra(intent, entry.getKey(), entry.getValue());
            }
        }
        return startActivityForResult(from, intent, requestCode);
    }

    /**
     * 跳转至Activity页面
     *
     * @param action 隐式启动的动作
     * @param key
     * @param param
     */
    public static boolean startActivityWithBundle(final String action, final String key, final Object param) {
        Intent intent = getActivityIntent(action);
        Bundle bundle = new Bundle();
        IntentUtils.putBundle(bundle, key, param);
        intent.putExtras(bundle);
        return startActivity(intent);
    }

    /**
     * 跳转至Activity页面
     *
     * @param from        Activity类
     * @param action      隐式启动的动作
     * @param requestCode 请求码
     * @param key
     * @param param
     */
    public static boolean startActivityForResultWithBundle(final Activity from, final String action, final int requestCode, final String key, final Object param) {
        Intent intent = getActivityIntent(from, action);
        Bundle bundle = new Bundle();
        IntentUtils.putBundle(bundle, key, param);
        intent.putExtras(bundle);
        return startActivityForResult(from, intent, requestCode);
    }


    /**
     * 跳转至Activity页面
     *
     * @param action 隐式启动的动作
     * @param map    携带的数据
     */
    public static boolean startActivityWithBundle(final String action, final Map<String, Object> map) {
        Intent intent = getActivityIntent(action);
        if (map != null && !map.isEmpty()) {
            Bundle bundle = new Bundle();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                IntentUtils.putBundle(bundle, entry.getKey(), entry.getValue());
            }
            intent.putExtras(bundle);
        }
        return startActivity(intent);
    }

    /**
     * 跳转至Activity页面
     *
     * @param from        Activity类
     * @param action      隐式启动的动作
     * @param requestCode 请求码
     * @param map         携带的数据
     */
    public static boolean startActivityForResultWithBundle(final Activity from, final String action, final int requestCode, final Map<String, Object> map) {
        Intent intent = getActivityIntent(from, action);
        if (map != null && !map.isEmpty()) {
            Bundle bundle = new Bundle();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                IntentUtils.putBundle(bundle, entry.getKey(), entry.getValue());
            }
            intent.putExtras(bundle);
        }
        return startActivityForResult(from, intent, requestCode);
    }

}
