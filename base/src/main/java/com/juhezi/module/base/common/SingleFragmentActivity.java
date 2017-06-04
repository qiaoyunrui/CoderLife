package com.juhezi.module.base.common;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Activity-Single-Fragment Base
 * Created by Juhezi[juhezix@163.com] on 2017/5/24.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {

    protected abstract Fragment createFragment();

    protected void init(@Nullable Bundle savedInstanceState) {
    }

    /**
     * Activity Layout Resource
     * @return Layout Resource
     */
    @LayoutRes
    protected abstract int getLayoutResId();

    /**
     * Fragment View Wrapper Id
     *
     * @return
     */
    @IdRes
    protected abstract int getFragmentWrapperId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(getFragmentWrapperId());
        if (null == fragment) {
            fragment = createFragment();
            fm.beginTransaction()
                    .add(getFragmentWrapperId(), fragment)
                    .commit();
        }
        init(savedInstanceState);
    }
}
