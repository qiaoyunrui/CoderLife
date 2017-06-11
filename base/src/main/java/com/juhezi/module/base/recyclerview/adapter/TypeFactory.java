package com.juhezi.module.base.recyclerview.adapter;

import android.view.View;

/**
 * Created by Juhezi[juhezix@163.com] on 2017/6/11.
 */

public interface TypeFactory {

    /**
     * 在 onCreateViewHolder 中直接调用
     * @param type
     * @param itmeView
     * @return
     */
    BaseViewHolder createViewHolder(int type, View itmeView);

}
