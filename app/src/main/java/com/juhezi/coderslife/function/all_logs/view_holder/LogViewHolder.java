package com.juhezi.coderslife.function.all_logs.view_holder;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.juhezi.coderslife.R;
import com.juhezi.coderslife.databinding.ItemAllLogsBinding;
import com.juhezi.coderslife.entry.LogContent;
import com.juhezi.coderslife.multitype.viewholder.BaseViewHolder;

/**
 * Created by qiao1 on 2017/2/15.
 */
public class LogViewHolder extends BaseViewHolder<LogContent, ItemAllLogsBinding> {
    private static String TAG = "LogViewHolder";

    private ImageView mImgType;
    private View mVdivider;   //分割线

    public LogViewHolder(View itemView, ItemAllLogsBinding binding, RecyclerView.Adapter adapter) {
        super(itemView, binding, adapter);
        mImgType = (ImageView) getView(R.id.img_item_all_logs_icon);
        mVdivider = getView(R.id.v_item_all_logs);
    }

    @Override
    public void bindViewData(LogContent data, int position) {
        binding.setLog(data);
        binding.executePendingBindings();
        switch (data.getContentType()) {
            case LogContent.TYPE_REQUIREMENT:   //需求
                mImgType.setImageResource(R.drawable.ic_requirement);
                break;
            case LogContent.TYPE_BUG:
                mImgType.setImageResource(R.drawable.ic_bug);
                break;
            case LogContent.TYPE_VERSION:
                mImgType.setImageResource(R.drawable.ic_version);
                break;
            case LogContent.TYPE_ERROR:
                mImgType.setImageResource(R.drawable.ic_error);
                break;
        }
        //是否显示分割线
        if (position == adapter.getItemCount() - 1) //最后一个Item
            mVdivider.setVisibility(View.INVISIBLE);
        else
            mVdivider.setVisibility(View.VISIBLE);
    }
}
