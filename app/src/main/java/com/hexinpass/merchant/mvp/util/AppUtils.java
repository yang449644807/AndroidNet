package com.hexinpass.merchant.mvp.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;

public class AppUtils {
    private static Boolean isDebug = null;

    public static void syncIsDebug(Context context){
        if(isDebug == null){
            isDebug = context.getApplicationInfo() != null &&
                    (context.getApplicationInfo().flags &
                            ApplicationInfo.FLAG_DEBUGGABLE) !=0;
        }
    }

    public static Boolean isDebug() {
        return isDebug == null ? false : isDebug.booleanValue();
    }
}
