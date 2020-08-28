package com.hexinpass.merchant.mvp.base;

import android.support.annotation.NonNull;

public interface IPresenter<V extends IView> {

    /**
     * 当与View发生连接的时候调用
     * @param view 依附的View
     */
    void attachView(@NonNull V view);

    /** 当View创建的时候调用*/
    void onCreate();

//    void onResume();

    /** 当View销毁的时候调用*/
    void onDestroy();

}
