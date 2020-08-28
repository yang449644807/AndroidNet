package com.hexinpass.merchant.mvp.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.hexinpass.merchant.common.AppConstants;

import java.util.Map;

public class SpUtils {

    private SharedPreferences sp;
    private SharedPreferences.Editor edit;
    private static SpUtils instance = new SpUtils();

    public static SpUtils getInstance() {
        return instance;
    }

    private SpUtils() {
        this(UiUtils.getContext().getSharedPreferences(AppConstants.FILE_NAME, Context.MODE_PRIVATE));
    }

    private SpUtils(SharedPreferences sp) {
        this.sp = sp;
        edit = sp.edit();
    }

    public SharedPreferences getSharedPreferences() {
        return sp;
    }

    public void putValue(String key, boolean value) {
        edit.putBoolean(key, value);
        edit.commit();
    }

    public void putValue(String key, float value) {
        edit.putFloat(key, value);
        edit.commit();
    }

    public void putValue(String key, int value) {
        edit.putInt(key, value);
        edit.commit();
    }

    public void putValue(String key, long value) {
        edit.putLong(key, value);
        edit.commit();
    }

    public void putValue(String key, String value) {
        edit.putString(key, value);
        edit.commit();
    }


    public boolean getBoolValue(String key) {
        return sp.getBoolean(key, false);
    }

    public float getFloatValue(String key) {
        return sp.getFloat(key, 0);
    }

    public int getIntegerValue(String key) {
        return sp.getInt(key, 0);
    }

    public long getLongValue(String key) {
        return sp.getLong(key, 0);
    }

    public String getStringValue(String key) {
        return sp.getString(key, "");
    }

    /**
     * 移除某个key值已经对应的值
     */
    public void remove(String key) {
        edit.remove(key);
        edit.commit();
    }

    public void clear() {
        edit.clear();
        edit.commit();
    }

    /**
     * 查询某个key是否已经存在
     *
     * @return
     */
    public boolean contains(String key) {
        return sp.contains(key);
    }


    /**
     * 返回所有的键值对
     *
     * @return
     */
    public Map<String, ?> getAll() {
        return sp.getAll();
    }

}
