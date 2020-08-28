package com.hexinpass.merchant.mvp.ui.contract;

import com.hexinpass.merchant.mvp.base.IPresenter;
import com.hexinpass.merchant.mvp.base.IView;
import com.hexinpass.merchant.mvp.bean.Adv;

public interface MainContract {
    interface Presenter extends IPresenter<View> {
        void getTest();
    }

    interface View extends IView {
        void success(Adv bean);
    }
}
