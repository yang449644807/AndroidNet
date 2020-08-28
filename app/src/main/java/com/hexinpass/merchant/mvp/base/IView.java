package com.hexinpass.merchant.mvp.base;

public interface IView {

    void showProgress(String msg);

    void hideProgress();

    void showMsg(String message);

}
