package com.hexinpass.merchant.common.rx;


import com.hexinpass.merchant.common.callback.RequestCallBack;
import com.hexinpass.merchant.mvp.util.NetUtil;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 功能描述: 发送HTTP请求时的默认订阅者。
 *      此订阅者默认处理的行为： 1、建立订阅关系时 2、发生错误时 3、接受到事件时
 */
public class DefaultHttpSubscriber<T> implements Observer<T> {

    private final RequestCallBack<T> callBack;

    public DefaultHttpSubscriber(RequestCallBack<T> callBack) {
        this.callBack = callBack;
    }

    @Override
    public void onError(Throwable e) {
        if (callBack != null) {
            callBack.onError(NetUtil.analyzeNetworkError(e));
        }
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Disposable d) {
        if (callBack != null) {
            callBack.beforeRequest(d);
        }
    }

    @Override
    public void onNext(T data) {
        if (callBack != null) {
            callBack.onSuccess(data);
        }
    }

}
