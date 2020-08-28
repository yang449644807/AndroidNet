package com.hexinpass.merchant.common.callback;

import io.reactivex.disposables.Disposable;

public interface RequestCallBack<T> {

    void beforeRequest(Disposable d);

    void onSuccess(T data);

    void onError(String errorMsg);
}
