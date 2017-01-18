package com.juhezi.coderslife;

import android.content.Context;
import android.databinding.ViewDataBinding;

import com.juhezi.coderslife.model.Response;
import com.juhezi.coderslife.model.ResponseImpl;

/**
 * Created by qiao1 on 2017/1/18.
 */
public abstract class BaseViewModel<T extends ViewDataBinding> {
    private static String TAG = "BaseViewModel";

    protected T binding;
    protected Context mContext;
    protected Response mResponse;

    public BaseViewModel(T binding, Context context) {
        this.binding = binding;
        this.mContext = context;
        this.mResponse = ResponseImpl.getInstance(mContext);
        initData();
    }

    /**
     * 初始化数据
     */
    protected abstract void initData();

}
