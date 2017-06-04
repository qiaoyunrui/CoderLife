package com.juhezi.module.base.router.operator;

import android.content.Context;
import android.content.Intent;

import com.juhezi.module.base.router.JUri;
import com.juhezi.module.base.router.exception.DestNotFoundException;

import java.util.HashMap;

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
    public void put(JUri JUri, Class<T> clazz) {
        if (mIntentMap != null) {
            mIntentMap.put(JUri.toString(), clazz);
        }
    }

    @Override
    public Intent invoke(JUri JUri, Context context) throws DestNotFoundException {
        Class<T> clazz = null;
        if (check(JUri)) {
            clazz = mIntentMap.get(JUri.toString());
        }
        if (clazz == null) {    //路由表并没这个地址
            throw new DestNotFoundException(JUri.toString());
        }
        return new Intent(context, clazz);
    }

    @Override
    public boolean check(JUri JUri) {
        return mIntentMap != null &&
                mIntentMap.keySet().contains(JUri.toString());
    }
}
