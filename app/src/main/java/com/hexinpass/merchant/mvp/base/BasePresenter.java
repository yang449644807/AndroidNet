package com.hexinpass.merchant.mvp.base;

import android.support.annotation.NonNull;

import com.hexinpass.merchant.common.callback.RequestCallBack;

import java.lang.ref.SoftReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter<V extends IView, E> implements IPresenter<V>, RequestCallBack<E> {

    protected CompositeDisposable mCompositeSubscription = new CompositeDisposable();

    /**
     * view 的弱引用,当内存不足释放内存
     */
    protected SoftReference<V> mViewRef;

    @Override
    public void onCreate() {
    }

    @Override
    public void attachView(@NonNull V view) {
        mViewRef = new SoftReference<>(view);
    }


    /**
     * 获取view的方法
     *
     * @return 当前关联的view
     */
    public V getView() {
        if (mViewRef == null){
            return null;
        }
        return mViewRef.get();
    }

    @Override
    public void onDestroy() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
        mCompositeSubscription.clear();
    }

    @Override
    public void beforeRequest(Disposable d) {
        V view = getView();
        if (view != null) {
            view.showProgress(null);
        }
    }

    @Override
    public void onSuccess(E data) {
        V view = getView();
        if (view != null) {
            view.hideProgress();
        }
    }

    @Override
    public void onError(String errorMsg) {
        V view = getView();
        if (view != null) {
            view.hideProgress();
            view.showMsg(errorMsg);
        }
    }

}
