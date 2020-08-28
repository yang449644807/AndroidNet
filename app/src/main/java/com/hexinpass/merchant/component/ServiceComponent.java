package com.hexinpass.merchant.component;

import android.content.Context;

import com.hexinpass.merchant.module.ServiceModule;
import com.hexinpass.merchant.scope.ForApplication;
import com.hexinpass.merchant.scope.ForService;
import com.hexinpass.merchant.scope.PerService;

import dagger.Component;

@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {

    @ForService
    Context getServiceContext();

    @ForApplication
    Context getApplicationContext();
}
