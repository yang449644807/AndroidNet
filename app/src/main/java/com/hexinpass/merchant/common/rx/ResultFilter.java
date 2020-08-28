package com.hexinpass.merchant.common.rx;

import com.hexinpass.merchant.exception.ApiException;
import com.hexinpass.merchant.mvp.bean.BaseBean;
import com.hexinpass.merchant.mvp.util.RxBus;

import io.reactivex.functions.Function;


/**
 * 功能描述: 请求结果的统一处理。只有请求成功是才会到业务层。
 */
public class ResultFilter<T> implements Function<BaseBean<T>, T> {

    @Override
    public T apply(BaseBean<T> tBaseBean) {
        if(tBaseBean == null) {
            throw new ApiException(0);
        }
        if (tBaseBean.errorCode != 0) {
            RxBus.getInstance().post(tBaseBean);
            throw new ApiException(tBaseBean.errorCode, tBaseBean.error);
        }
        return tBaseBean.data;
    }
}
