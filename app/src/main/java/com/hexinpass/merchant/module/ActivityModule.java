package com.hexinpass.merchant.module;

import android.app.Activity;
import android.content.Context;


import com.hexinpass.merchant.scope.ForActivity;
import com.hexinpass.merchant.scope.PerActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @PerActivity
    @ForActivity
    public Context provideActivityContext() {
        return mActivity;
    }

    @Provides
    @PerActivity
    public Activity provideActivity() {
        return mActivity;
    }

}
