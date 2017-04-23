package com.juhezi.module.base.router.operator;

import android.content.Context;
import android.content.Intent;

import com.juhezi.module.base.router.URI;
import com.juhezi.module.base.router.exception.DestNotFoundException;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Intent 跳转的操作
 * <p>
 * Created by Juhezi[juhezix@163.com] on 2017/4/23.
 */

public abstract class IntentOperator<T> implements
        IOperator<T, Intent> {

    private HashMap<String, Class<T>> mIntentMap;   //路由表，url -> Class

    public IntentOperator() {
        mIntentMap = new HashMap<>();
    }

    @Override
    public void put(URI uri, Class<T> clazz) {
        if (mIntentMap != null) {
            mIntentMap.put(uri.toString(), clazz);
        }
    }

    @Override
    public Intent invoke(URI uri, Context context) throws DestNotFoundException {
        Class<T> clazz = null;
        if (check(uri)) {
            clazz = mIntentMap.get(uri.toString());
        }
        if (clazz == null) {    //路由表并没这个地址
            throw new DestNotFoundException(uri.toString());
        }
        return new Intent(context, clazz);
    }

    @Override
    public boolean check(URI uri) {
        return mIntentMap != null &&
                mIntentMap.keySet().contains(uri.toString());
    }
}
