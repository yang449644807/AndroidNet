package com.hexinpass.merchant.mvp.util;

import com.google.gson.Gson;
import com.hexinpass.merchant.BuildConfig;
import com.hexinpass.merchant.common.AppConstants;

import java.util.HashMap;
import java.util.Map;

public class JsonUtils {

    public static String paramToJson(int cmd, Map<String, Object> param) {
        String result = null;
        Map<String, Object> map = new HashMap<String, Object>();
        String sid = SpUtils.getInstance().getStringValue(AppConstants.USER_SID);
        map.put("sid", sid);
        map.put("cmd",cmd);
        map.put("versions", BuildConfig.VERSION_NAME);
        Gson gson = new Gson();
        String data = gson.toJson(param);
        map.put("data", data);
        result = gson.toJson(map);
        return result;
    }
}
