package com.hexinpass.merchant.mvp.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.hexinpass.merchant.MyApplication;
import com.hexinpass.merchant.R;
import com.hexinpass.merchant.exception.ApiException;

import retrofit2.HttpException;


public class NetUtil {

    /**
     * 检查当前网络是否可用
     *
     * @return 是否连接到网络
     */
    public static boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) MyApplication.getAppContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            NetworkInfo info = connectivityManager.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isNetworkErrThenShowMsg() {
        if (!isNetworkAvailable()) {
            Toast.makeText(MyApplication.getAppContext(),MyApplication.getAppContext()
                    .getString(R.string.internet_error),Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    public static String analyzeNetworkError(Throwable e) {
        String errMsg = UiUtils.getString(R.string.load_error);
        if (!NetUtil.isNetworkAvailable()) {
            errMsg = UiUtils.getString(R.string.retry_after);
        }
        if (e instanceof HttpException) {
            int state = ((HttpException) e).code();
//            if (state == 403) {
//                errMsg = UiUtils.getString(R.string.load_error);
//            }
        }
        if (e instanceof ApiException) {
            errMsg = e.getMessage();
        }
        return errMsg;
    }

}
