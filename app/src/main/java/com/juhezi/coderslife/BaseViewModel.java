package com.juhezi.coderslife;

import android.databinding.ViewDataBinding;

/**
 * Created by qiao1 on 2017/1/18.
 */
public abstract class BaseViewModel<T extends ViewDataBinding> {
    private static String TAG = "BaseViewModel";

    protected T binding;

    public BaseViewModel(T binding) {
        this.binding = binding;
        initData();
    }

    /**
     * 初始化数据
     */
    protected abstract void initData();

}
