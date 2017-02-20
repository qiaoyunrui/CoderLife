package com.juhezi.coderslife.multitype.factory;

import android.support.annotation.IdRes;
import android.support.annotation.IntDef;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.juhezi.coderslife.R;
import com.juhezi.coderslife.entry.LogContent;
import com.juhezi.coderslife.function.all_logs.bean.ProgressBean;
import com.juhezi.coderslife.function.all_logs.bean.TimeBean;
import com.juhezi.coderslife.function.draft_box.bean.LogDraftBean;
import com.juhezi.coderslife.multitype.viewholder.BaseViewHolder;

/**
 * 需要持有一个Adapter对象
 * Created by qiao1 on 2017/2/15.
 */
public abstract class TypeFactory {

    public abstract int type(LogContent logEntry);

    public abstract int type(TimeBean timeBean);

    public abstract int type(ProgressBean progressBean);

    public abstract int type(LogDraftBean logDraftBean);

    protected RecyclerView.Adapter adapter;

    public abstract BaseViewHolder createViewHolder(int type, View itemView);

    public TypeFactory(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
    }

}
