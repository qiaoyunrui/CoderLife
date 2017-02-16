package com.juhezi.coderslife.multitype.viewholder;

import android.databinding.ViewDataBinding;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by qiao1 on 2017/2/15.
 */
public abstract class BaseViewHolder<T, Binding extends ViewDataBinding> extends RecyclerView.ViewHolder {
    private static String TAG = "BaseViewHolder";

    SparseArray<View> mViews;
    protected Binding binding;
    protected RecyclerView.Adapter adapter;

    public BaseViewHolder(View itemView, Binding binding, RecyclerView.Adapter adapter) {
        super(itemView);
        this.adapter = adapter;
        this.binding = binding;
        mViews = new SparseArray<>();
    }

    public View getView(@IdRes int resId) {
        View view = mViews.get(resId);
        if (view == null) {
            view = itemView.findViewById(resId);
            mViews.put(resId, view);
        }
        return view;
    }

    /**
     * 绑定数据
     *
     * @param data
     * @param position 当前位置
     */
    public abstract void bindViewData(T data, int position);
}
