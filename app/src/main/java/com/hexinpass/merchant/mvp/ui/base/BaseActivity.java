package com.hexinpass.merchant.mvp.ui.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.hexinpass.merchant.MyApplication;
import com.hexinpass.merchant.R;
import com.hexinpass.merchant.component.ActivityComponent;
import com.hexinpass.merchant.component.DaggerActivityComponent;
import com.hexinpass.merchant.module.ActivityModule;
import com.hexinpass.merchant.mvp.base.IPresenter;
import com.hexinpass.merchant.mvp.base.IView;
import com.hexinpass.merchant.mvp.bean.BaseBean;
import com.hexinpass.merchant.mvp.util.NetUtil;
import com.hexinpass.merchant.mvp.util.RxBus;
import com.hexinpass.merchant.mvp.widget.TitleBarView;

import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseActivity<V extends IView, P extends IPresenter<V>> extends
        AppCompatActivity implements IView {

    /**
     * Activity注入器
     */
    protected ActivityComponent mActivityComponent;
    protected P mPresenter;
    protected CompositeDisposable mCompositeSubscription = new CompositeDisposable();
    protected ProgressDialog mProgressDialog;


    /**
     * 返回布局layout
     */
    @LayoutRes
    public abstract int getLayoutId();

    /**
     * 注入对象
     */
    public abstract void initInjector();

    /**
     * 返回注入的Presenter, 如果返回null则不可使用 {@code mPresenter}
     */
    @Nullable
    public abstract P createPresenter();

    public abstract void initViews(Bundle savedInstanceState);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        beforeInvokeSuperOnCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        NetUtil.isNetworkErrThenShowMsg();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initActivityComponent();
        setContentView(getLayoutId());
        initInjector();
        mPresenter = createPresenter();
        ButterKnife.bind(this);
        if (mPresenter != null) {
            mPresenter.attachView((V) this);
            mPresenter.onCreate();
        }
        TitleBarView titleBar = dealTitleBar();
        setStatusBar(titleBar);
        initViews(savedInstanceState);

        Disposable disposable = RxBus.getInstance()
                .toObservable(BaseBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(event -> {
                    hideProgress();
                    if (event != null) {
                        Toast.makeText(this,event.error,Toast.LENGTH_SHORT).show();
                    }
                });

        mCompositeSubscription.add(disposable);
    }

    private void initActivityComponent() {
        mActivityComponent = DaggerActivityComponent.builder()
                .applicationComponent(((MyApplication) getApplication()).getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

    protected void setStatusBar(TitleBarView titleBar) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //设置状态栏颜色 透明
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            if (titleBar == null) {
                getWindow().setStatusBarColor(Color.TRANSPARENT);
            } else {
                getWindow().setStatusBarColor(titleBar.getBgColor());
            }
            //设置主题为亮色主题，状态栏文字为黑色。
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    final TitleBarView dealTitleBar() {
        // 处理页面返回按钮点击事件
        View view = findViewById(R.id.title_bar);
        if (view != null && view instanceof TitleBarView) {
            TitleBarView titleBar = (TitleBarView) view;
            titleBar.setOnLeftBtnClickListener(v -> {
                onBeforeFinish();
                this.onBackPressed();
            });
            return titleBar;
        }
        return null;
    }

    /**
     * 此方法在super.onCreate()之前调用
     */
    protected void beforeInvokeSuperOnCreate(Bundle savedInstanceState) {
    }

    /**
     * 在调用activity的finish方法之前调用
     */
    protected void onBeforeFinish() {
        // 空实现，子类根据需要选择重写
    }

    /**
     * 显示进度对话框 (根据需要，子类可以选择性重写)
     *
     * @param msg 对话框显示的文字 如果为""或null,默认显示 加载中...
     */
    @Override
    public void showProgress(String msg) {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
        }
        if (TextUtils.isEmpty(msg)) {
            msg = getString(R.string.loading);
        }
        mProgressDialog.setMessage(msg);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.setCancelable(true);
        if (mProgressDialog.isShowing()) {
            return;
        }
        mProgressDialog.show();
    }

    /* 根据需要，子类可以选择性重写*/
    @Override
    public void hideProgress() {
//        if (mProgressDialog == null) {
//            mProgressDialog = new ProgressDialog(this, ProgressDialog.STYLE_SPINNER);
//        }
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showMsg(String message) {
        // 根据需要，子类可以选择性重写
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
