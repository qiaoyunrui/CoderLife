package com.juhezi.coderslife.function.all_logs;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.juhezi.coderslife.R;
import com.juhezi.coderslife.databinding.ItemAllLogsBinding;
import com.juhezi.coderslife.entry.LogContent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiao1 on 2017/1/25.
 */
public class AllLogsAdapter extends RecyclerView.Adapter<AllLogsAdapter.LogItemHolder> {
    private static String TAG = "AllLogsAdapter";

    private List<LogContent> logContents = new ArrayList<>();

    private View mEmptyView;

    @Override
    public LogItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_all_logs, parent, false);
        ItemAllLogsBinding binding = DataBindingUtil.bind(itemView);
        return new LogItemHolder(itemView, binding);
    }

    @Override
    public void onBindViewHolder(LogItemHolder holder, int position) {
        holder.binding.setLog(logContents.get(position));
        holder.binding.executePendingBindings();
        switch (logContents.get(position).getContentType()) {
            case LogContent.TYPE_REQUIREMENT:
                holder.mImgType.setImageResource(R.drawable.ic_requirement);
                break;
            case LogContent.TYPE_BUG:
                holder.mImgType.setImageResource(R.drawable.ic_bug);
                break;
            case LogContent.TYPE_VERSION:
                holder.mImgType.setImageResource(R.drawable.ic_version);
                break;
            case LogContent.TYPE_ERROR:
                holder.mImgType.setImageResource(R.drawable.ic_error);
                break;
        }
        if (position == logContents.size() - 1) {    //是最后一个Item
            holder.mVdivider.setVisibility(View.INVISIBLE);
        } else {
            holder.mVdivider.setVisibility(View.VISIBLE);
        }
    }

    public void setLogContents(List<LogContent> logContents) {
        this.logContents = logContents;
        notifyDataSetChanged();
    }

    public void setEmptyView(View emptyView) {
        this.mEmptyView = mEmptyView;
    }

    @Override
    public int getItemCount() {
        if (mEmptyView != null) {
            if (logContents.size() == 0) {
                mEmptyView.setVisibility(View.VISIBLE);
            } else {
                mEmptyView.setVisibility(View.INVISIBLE);
            }
        }
        return logContents.size();
    }

    public class LogItemHolder extends RecyclerView.ViewHolder {

        private ImageView mImgType;
        private View mVdivider;   //分割线
        private ItemAllLogsBinding binding;

        public LogItemHolder(View itemView, ItemAllLogsBinding binding) {
            super(itemView);
            this.binding = binding;
            mImgType = binding.imgItemAllLogsIcon;
            mVdivider = binding.vItemAllLogs;
        }
    }

}
