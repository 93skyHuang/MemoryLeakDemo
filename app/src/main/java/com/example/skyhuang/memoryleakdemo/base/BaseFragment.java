package com.example.skyhuang.memoryleakdemo.base;

import android.support.v4.app.Fragment;

import com.example.skyhuang.memoryleakdemo.SkyApplication;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by skyHuang on 2018/9/26.
 */

public abstract class BaseFragment extends Fragment {

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = SkyApplication.getRefWatcher(getActivity());
        refWatcher.watch(this);
    }
}
