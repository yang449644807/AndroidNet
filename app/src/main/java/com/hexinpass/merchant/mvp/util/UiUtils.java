package com.hexinpass.merchant.mvp.util;

import android.content.Context;
import android.content.res.Resources;

import com.hexinpass.merchant.MyApplication;

public class UiUtils {

    /**
     * 获得Application对应的Context
     */
    public static Context getContext() {
        return MyApplication.getAppContext();
    }

    public static Resources getResourse() {
        return getContext().getResources();
    }

    /**
     * 通过资源id获取string文件中的字符串
     */
    public static String getString(int resId) {
        return getResourse().getString(resId);
    }

    /**
     * px转换dip
     */
    public static int px2dip(int px) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }
}
