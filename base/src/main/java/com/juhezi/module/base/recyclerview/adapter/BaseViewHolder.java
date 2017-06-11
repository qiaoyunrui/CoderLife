package com.juhezi.module.base.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Juhezi[juhezix@163.com] on 2017/6/11.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    /**
     * 为 Item 绑定数据
     * @param data
     */
    public abstract void bindViewData(T data);

}
