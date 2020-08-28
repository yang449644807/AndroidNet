package com.hexinpass.merchant.component;

import android.content.Context;

import com.hexinpass.merchant.module.ApplicationModule;
import com.hexinpass.merchant.repository.ApiService;
import com.hexinpass.merchant.scope.ForApplication;
import com.hexinpass.merchant.scope.PerApp;

import dagger.Component;

@PerApp
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ForApplication
    Context getApplication();

    ApiService getApiService();

}

