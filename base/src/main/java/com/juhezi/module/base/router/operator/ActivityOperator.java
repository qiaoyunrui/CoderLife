package com.juhezi.module.base.router.operator;

import android.support.v7.app.AppCompatActivity;

import com.juhezi.module.base.router.JUri;

/**
 * Created by Juhezi[juhezix@163.com] on 2017/4/23.
 */

public class ActivityOperator extends IntentOperator<AppCompatActivity> {

    @JUri.Protocol
    public static final String PROTOCOL = JUri.ACTIVITY;

}
