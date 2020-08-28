package com.hexinpass.merchant.repository;

import com.hexinpass.merchant.mvp.bean.Adv;
import com.hexinpass.merchant.mvp.bean.BaseBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    /**
     * 开屏广告
     */
    @POST("/api")
    Observable<BaseBean<Adv>> getStartAdv(@Body RequestBody requestBody);
}
