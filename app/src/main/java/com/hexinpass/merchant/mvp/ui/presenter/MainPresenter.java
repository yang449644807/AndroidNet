package com.hexinpass.merchant.mvp.ui.presenter;

import android.util.Log;

import com.hexinpass.merchant.common.callback.RequestCallBack;
import com.hexinpass.merchant.common.rx.DefaultHttpSubscriber;
import com.hexinpass.merchant.common.rx.ResultFilter;
import com.hexinpass.merchant.common.rx.TransformUtils;
import com.hexinpass.merchant.mvp.base.BasePresenter;
import com.hexinpass.merchant.mvp.bean.Adv;
import com.hexinpass.merchant.mvp.ui.contract.MainContract;
import com.hexinpass.merchant.mvp.util.JsonUtils;
import com.hexinpass.merchant.repository.ApiService;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class MainPresenter extends BasePresenter<MainContract.View, Void>
        implements MainContract.Presenter {

    private final ApiService apiService;

    @Inject
    public MainPresenter(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public void getTest() {
        Map<String, Object> map = new HashMap<>();
        String param = JsonUtils.paramToJson(131, map);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), param.toString());
        apiService.getStartAdv(requestBody)
                .map(new ResultFilter<>())
                .compose(TransformUtils.defaultSchedulers())
                .subscribe(new DefaultHttpSubscriber<>(new RequestCallBack<Adv>() {
                    @Override
                    public void beforeRequest(Disposable d) {
                        mCompositeSubscription.add(d);
                    }

                    @Override
                    public void onSuccess(Adv data) {
                        getView().success(data);
                    }

                    @Override
                    public void onError(String errorMsg) {
                        Log.e("I/Request",errorMsg);
                    }
                }));
    }
}
