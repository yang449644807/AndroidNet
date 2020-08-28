package com.hexinpass.merchant;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;

import com.facebook.stetho.Stetho;
import com.hexinpass.merchant.component.ApplicationComponent;
import com.hexinpass.merchant.component.DaggerApplicationComponent;
import com.hexinpass.merchant.module.ApplicationModule;
import com.hexinpass.merchant.mvp.util.AppUtils;

public class MyApplication extends Application {

    private static Context context;
    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        initApplicationComponent();

        AppUtils.syncIsDebug(getApplicationContext());
        if (AppUtils.isDebug()) {
            //初始化第三方数据拦截
            Stetho.initializeWithDefaults(this);
        }
        //（推荐）7.0之后你的app就算有权限，给出一个URI之后手机也认为你没有权限。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
    }

    public static Context getAppContext() {
        return context;
    }

    private void initApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
