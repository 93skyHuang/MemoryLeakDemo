package com.example.skyhuang.memoryleakdemo;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 *
 * @author skyHuang
 * @date 2018/9/26
 *
 */

public class SkyApplication extends Application {

    private RefWatcher refWatcher;


    @Override
    public void onCreate() {
        super.onCreate();
        refWatcher = LeakCanary.install(this);
    }

    public static RefWatcher getRefWatcher(Context context) {
        SkyApplication application = (SkyApplication) context.getApplicationContext();
        return application.refWatcher;
    }

}
