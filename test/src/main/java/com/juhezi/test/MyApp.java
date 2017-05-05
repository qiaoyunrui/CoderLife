package com.juhezi.test;

import android.app.Application;

import com.juhezi.module.base.router.operator.manager.OMP;

/**
 * Created by Juhezi[juhezix@163.com] on 2017/5/2.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        OMP.putURI(MainActivity.URL, MainActivity.class);
    }
}
