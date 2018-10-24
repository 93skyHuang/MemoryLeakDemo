package com.example.skyhuang.memoryleakdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.example.skyhuang.memoryleakdemo.R;
import com.example.skyhuang.memoryleakdemo.base.BaseActivity;

/**
 * Created by skyHuang on 2018/9/26.
 */

public class WebActivity extends BaseActivity {
    private WebView mWebView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        mWebView = (WebView) findViewById(R.id.web);
        mWebView.loadUrl("http://www.cnblogs.com/whoislcj/p/5720202.html");

    }
}
