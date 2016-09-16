package com.example.albertlee.weathertalk;

import android.app.Application;

import com.thinkland.juheapi.common.CommonFun;

/**
 * Created by AlbertLee on 2016/8/30 23:59
 */
public class MApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CommonFun.initialize(getApplicationContext());
    }
}
