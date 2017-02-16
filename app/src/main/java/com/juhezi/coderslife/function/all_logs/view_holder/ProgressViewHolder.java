package com.juhezi.coderslife.function.all_logs.view_holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.juhezi.coderslife.databinding.ItemAllLogsProgressBinding;
import com.juhezi.coderslife.function.all_logs.bean.ProgressBean;
import com.juhezi.coderslife.multitype.viewholder.BaseViewHolder;

/**
 * Created by qiao1 on 2017/2/16.
 */
public class ProgressViewHolder extends BaseViewHolder<ProgressBean, ItemAllLogsProgressBinding> {
    private static String TAG = "ProgressViewHolder";

    public ProgressViewHolder(View itemView, ItemAllLogsProgressBinding binding, RecyclerView.Adapter adapter) {
        super(itemView, binding, adapter);
    }

    @Override
    public void bindViewData(ProgressBean data, int position) {

    }
}
