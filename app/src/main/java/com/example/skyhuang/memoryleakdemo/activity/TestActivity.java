package com.example.skyhuang.memoryleakdemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.skyhuang.memoryleakdemo.Manager;
import com.example.skyhuang.memoryleakdemo.MainActivity;
import com.example.skyhuang.memoryleakdemo.R;
import com.example.skyhuang.memoryleakdemo.base.BaseActivity;

import java.lang.ref.WeakReference;

/**
 * Created by skyHuang on 2018/9/26.
 */

public class TestActivity extends BaseActivity {

    private Handler mHandler = new Handler();
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Button button = findViewById(R.id.jump_btn);
        textView = findViewById(R.id.tv);
//        contextLeakDemo();
        handlerLeakDemo(1000,"1000毫秒");
        handlerLeakDemo( 10 * 1000,"10秒");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TestActivity.this, MainActivity.class));
                handlerLeakDemo( 30 * 1000,"30秒");
                finish();
            }
        });
    }


    /**
     * context导致的内存泄漏
     */
    private Manager manager;
    private void contextLeakDemo() {
        manager = Manager.getInstance(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
        mHandler=null;
//        SkyApplication.getRefWatcher(this).watch(this);
    }

    /**
     * Handler 造成的内存泄漏
     * 在destroy 方法中把这条handler消息给移除出去
     */
    private void handlerLeakDemo(long delayMillis, final String str) {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setText(str);
            }
        }, delayMillis);
    }

    /**
     * 消息发送造成的内存泄漏
     */

    static class TestHandler extends Handler {
        WeakReference<Activity> weakReference;
        public TestHandler(Activity activity){
            weakReference=new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            final Activity activity=weakReference.get();
            super.handleMessage(msg);
        }
    }
}
