package com.hexinpass.merchant.mvp.ui;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hexinpass.merchant.R;
import com.hexinpass.merchant.mvp.base.IPresenter;
import com.hexinpass.merchant.mvp.bean.Adv;
import com.hexinpass.merchant.mvp.ui.base.BaseActivity;
import com.hexinpass.merchant.mvp.ui.contract.MainContract;
import com.hexinpass.merchant.mvp.ui.presenter.MainPresenter;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainContract.View {

    @Inject
    MainPresenter presenter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }

    @Nullable
    @Override
    public IPresenter createPresenter() {
        return presenter;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        TextView tv = findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getTest();
            }
        });
    }


    @Override
    public void success(Adv bean) {
        Log.e("I/Request", ",success");
    }
}
