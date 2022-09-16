package com.sem.kingapputils.http;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import per.goweii.rxhttp.core.RxLife;
import per.goweii.rxhttp.request.RxRequest;
import per.goweii.rxhttp.request.exception.ExceptionHandle;

import java.util.List;

/**
 * @author CuiZhen
 * @date 2019/5/12
 * GitHub: https://github.com/goweii
 */
public class BaseHttpRequest {

    private static final String tag = "http->";

    protected static <T> Disposable request(@NonNull Observable<SemResponse<T>> observable,
                                            @NonNull RequestListener<T> listener) {
        return request(observable, listener, null);
    }

    protected static <T> Disposable request(@NonNull Observable<SemResponse<T>> observable,
                                            @NonNull RequestListener<T> listener,
                                            @Nullable ResponseToCache<T> responseToCache) {
        return RxRequest.create(observable)
                .listener(new RxRequest.RequestListener() {
                    @Override
                    public void onStart() {
                        listener.onStart();
                    }

                    @Override
                    public void onError(ExceptionHandle handle) {
                        handle.getException().printStackTrace();
                        Log.e(tag,handle.getMsg());
                        listener.onError(handle);
                        listener.onFailed(SemApi.ApiCode.ERROR, handle.getMsg());
                    }

                    @Override
                    public void onFinish() {
                        listener.onFinish();
                    }
                })
                .request(new RxRequest.ResultCallback<T>() {
                    @Override
                    public void onSuccess(int code, T data) {
                        boolean diffCache = true;
                        if (responseToCache != null) {
                            diffCache = responseToCache.onResponse(data);
                        }
                        if (diffCache) {
                            listener.onSuccess(code, data);
                        }
                    }

                    @Override
                    public void onFailed(int code, String msg) {
                        if (code == SemApi.ApiCode.FAILED_NOT_LOGIN) {
                            //登录失败
                        }
                        listener.onFailed(code, msg);
                    }
                });
    }

    protected static <T> void cacheBean(String key,
                                        Class<T> clazz,
                                        RequestListener<T> listener) {
        SemCache.getInstance().getBean(key, clazz, new CacheListener<T>() {
            @Override
            public void onSuccess(int code, T data) {
                listener.onSuccess(code, data);
            }

            @Override
            public void onFailed() {
                listener.onFailed(SemApi.ApiCode.FAILED_NO_CACHE, "");
            }
        });
    }

    protected static <T> void cacheList(String key,
                                        Class<T> clazz,
                                        RequestListener<List<T>> listener) {
        SemCache.getInstance().getList(key, clazz, new CacheListener<List<T>>() {
            @Override
            public void onSuccess(int code, final List<T> data) {
                listener.onSuccess(code, data);
            }

            @Override
            public void onFailed() {
                listener.onFailed(SemApi.ApiCode.FAILED_NO_CACHE, "");
            }
        });
    }

    protected static <T> void cacheAndNetList(RxLife rxLife,
                                              Observable<SemResponse<List<T>>> observable,
                                              String key,
                                              Class<T> clazz,
                                              RequestListener<List<T>> listener) {
        //默认不使用缓存
        cacheAndNetList(rxLife, observable, true, key, clazz, listener);
    }

    protected static <T> void cacheAndNetList(RxLife rxLife,
                                              Observable<SemResponse<List<T>>> observable,
                                              boolean refresh,
                                              String key,
                                              Class<T> clazz,
                                              RequestListener<List<T>> listener) {
        if (refresh) {
            rxLife.add(request(observable, listener, new ResponseToCache<List<T>>() {
                @Override
                public boolean onResponse(List<T> resp) {
                    SemCache.getInstance().save(key, resp);
                    return true;
                }
            }));
            return;
        }
        rxLife.add(SemCache.getInstance().getList(key, clazz, new CacheListener<List<T>>() {
            @Override
            public void onSuccess(int code, final List<T> data) {
                listener.onSuccess(code, data);
                rxLife.add(request(observable, listener, new ResponseToCache<List<T>>() {
                    @Override
                    public boolean onResponse(List<T> resp) {
                        if (SemCache.getInstance().isSame(data, resp)) {
                            return false;
                        }
                        SemCache.getInstance().save(key, resp);
                        return true;
                    }
                }));
            }

            @Override
            public void onFailed() {
                rxLife.add(request(observable, listener, new ResponseToCache<List<T>>() {
                    @Override
                    public boolean onResponse(List<T> resp) {
                        SemCache.getInstance().save(key, resp);
                        return true;
                    }
                }));
            }
        }));
    }

    protected static <T> void netList(RxLife rxLife,
                                      Observable<SemResponse<List<T>>> observable,
                                      String key,
                                      RequestListener<List<T>> listener) {
        rxLife.add(request(observable, listener, new ResponseToCache<List<T>>() {
            @Override
            public boolean onResponse(List<T> resp) {
                SemCache.getInstance().save(key, resp);
                return true;
            }
        }));
    }

    protected static <T> void cacheAndNetBean(RxLife rxLife,
                                              Observable<SemResponse<T>> observable,
                                              String key,
                                              Class<T> clazz,
                                              RequestListener<T> listener) {
        cacheAndNetBean(rxLife, observable, false, key, clazz, listener);
    }

    protected static <T> void cacheAndNetBean(RxLife rxLife,
                                              Observable<SemResponse<T>> observable,
                                              boolean refresh,
                                              String key,
                                              Class<T> clazz,
                                              RequestListener<T> listener) {
        if (refresh) {
            rxLife.add(request(observable, listener, new ResponseToCache<T>() {
                @Override
                public boolean onResponse(T resp) {
                    SemCache.getInstance().save(key, resp);
                    return true;
                }
            }));
            return;
        }
        rxLife.add(SemCache.getInstance().getBean(key, clazz, new CacheListener<T>() {
            @Override
            public void onSuccess(int code, T data) {
                listener.onSuccess(code, data);
                rxLife.add(request(observable, listener, new ResponseToCache<T>() {
                    @Override
                    public boolean onResponse(T resp) {
                        if (SemCache.getInstance().isSame(data, resp)) {
                            return false;
                        }
                        SemCache.getInstance().save(key, resp);
                        return true;
                    }
                }));
            }

            @Override
            public void onFailed() {
                rxLife.add(request(observable, listener, new ResponseToCache<T>() {
                    @Override
                    public boolean onResponse(T resp) {
                        SemCache.getInstance().save(key, resp);
                        return true;
                    }
                }));
            }
        }));
    }

    protected static <T> void cacheOrNetBean(RxLife rxLife,
                                             Observable<SemResponse<T>> observable,
                                             String key,
                                             Class<T> clazz,
                                             RequestListener<T> listener) {
        cacheOrNetBean(rxLife, observable, false, key, clazz, listener);
    }

    protected static <T> void cacheOrNetBean(RxLife rxLife,
                                             Observable<SemResponse<T>> observable,
                                             boolean refresh,
                                             String key,
                                             Class<T> clazz,
                                             RequestListener<T> listener) {
        if (refresh) {
            netBean(rxLife, observable, key, listener);
            return;
        }
        rxLife.add(SemCache.getInstance().getBean(key, clazz, new CacheListener<T>() {
            @Override
            public void onSuccess(int code, T data) {
                listener.onSuccess(code, data);
            }

            @Override
            public void onFailed() {
                netBean(rxLife, observable, key, listener);
            }
        }));
    }

    protected static <T> void netBean(RxLife rxLife,
                                      Observable<SemResponse<T>> observable,
                                      String key,
                                      RequestListener<T> listener) {
        rxLife.add(request(observable, listener, new ResponseToCache<T>() {
            @Override
            public boolean onResponse(T resp) {
                SemCache.getInstance().save(key, resp);
                return true;
            }
        }));
    }

    public interface ResponseToCache<T> {
        boolean onResponse(T resp);
    }

}
