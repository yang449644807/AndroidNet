package com.hexinpass.merchant.module;

import android.app.Service;
import android.content.Context;

import com.hexinpass.merchant.scope.ForService;
import com.hexinpass.merchant.scope.PerService;

import dagger.Module;
import dagger.Provides;

@Module
public class ServiceModule {

    private Service mService;

    public ServiceModule(Service service) {
        mService = service;
    }

    @Provides
    @PerService
    @ForService
    public Context ProvideServiceContext() {
        return mService;
    }

}
