package com.example.skyhuang.memoryleakdemo.base;

import android.support.v7.app.AppCompatActivity;

import com.example.skyhuang.memoryleakdemo.SkyApplication;

/**
 * Created by skyHuang on 2018/9/26.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SkyApplication.getRefWatcher(this).watch(this);
    }
}
