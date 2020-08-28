package com.hexinpass.merchant.component;

import android.app.Activity;
import android.content.Context;

import com.hexinpass.merchant.mvp.ui.MainActivity;
import com.hexinpass.merchant.module.ActivityModule;
import com.hexinpass.merchant.scope.ForActivity;
import com.hexinpass.merchant.scope.ForApplication;
import com.hexinpass.merchant.scope.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    @ForActivity
    Context getActivityContext();

    @ForApplication
    Context getApplicationContext();

    Activity getActivity();

    void inject(MainActivity activity);
}
