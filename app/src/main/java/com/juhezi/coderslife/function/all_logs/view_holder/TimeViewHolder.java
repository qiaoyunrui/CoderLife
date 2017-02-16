package com.juhezi.coderslife.function.all_logs.view_holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.juhezi.coderslife.databinding.ItemAllLogsTimeBinding;
import com.juhezi.coderslife.function.all_logs.bean.TimeBean;
import com.juhezi.coderslife.multitype.viewholder.BaseViewHolder;

/**
 * Created by qiao1 on 2017/2/16.
 */
public class TimeViewHolder extends BaseViewHolder<TimeBean, ItemAllLogsTimeBinding> {
    private static String TAG = "TimeViewHolder";

    public TimeViewHolder(View itemView, ItemAllLogsTimeBinding binding, RecyclerView.Adapter adapter) {
        super(itemView, binding, adapter);
    }

    @Override
    public void bindViewData(TimeBean data, int position) {
        binding.setTimeBean(data);
        binding.executePendingBindings();
    }
}
