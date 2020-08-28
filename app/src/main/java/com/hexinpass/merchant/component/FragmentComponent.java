package com.hexinpass.merchant.component;

import android.app.Activity;
import android.content.Context;

import com.hexinpass.merchant.module.FragmentModule;
import com.hexinpass.merchant.scope.ForActivity;
import com.hexinpass.merchant.scope.ForApplication;
import com.hexinpass.merchant.scope.PerFragment;

import dagger.Component;

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    @ForActivity
    Context getActivityContext();

    @ForApplication
    Context getApplicationContext();

    Activity getActivity();
}
