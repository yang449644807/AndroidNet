package com.hexinpass.merchant.module;

import android.content.Context;

import com.hexinpass.merchant.MyApplication;
import com.hexinpass.merchant.repository.ApiService;
import com.hexinpass.merchant.repository.RetrofitManager;
import com.hexinpass.merchant.scope.ForApplication;
import com.hexinpass.merchant.scope.PerApp;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private MyApplication mApplication;

    public ApplicationModule(MyApplication application) {
        mApplication = application;
    }

    @Provides
    @PerApp
    @ForApplication
    public Context provideApplicationContext() {
        return mApplication.getApplicationContext();
    }

    @Provides
    @PerApp
    public ApiService provideApiService() {
        return RetrofitManager.getInstance().getApiService();
    }


}
