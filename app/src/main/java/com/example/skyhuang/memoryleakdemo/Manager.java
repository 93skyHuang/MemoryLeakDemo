package com.example.skyhuang.memoryleakdemo;

import android.content.Context;

/**
 * Created by skyHuang on 2018/9/26.
 */

public class Manager {
    public static Manager mInstance;
    private Context mContext;


    private Manager(Context context) {
        //fixme  如果context 是activity 的上下文当这个activity 没有使用的时候但是这个context 还在使用的时候系统是无法回收的此时就会造成内存泄漏
//        mContext = context;

        //fixed  mContext = context.getApplicationContext();
        mContext = context.getApplicationContext();
    }

    public static Manager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (Manager.class) {
                mInstance = new Manager(context);
            }
        }
        return mInstance;
    }

    public Context getmContext() {
        return mContext;
    }
}
