package com.juhezi.coderslife.function.main;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.juhezi.coderslife.R;
import com.juhezi.coderslife.databinding.ItemLogBinding;
import com.juhezi.coderslife.entry.LogContent;
import com.juhezi.coderslife.tools.Action1;
import com.juhezi.coderslife.tools.Action2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiao1 on 2017/1/18.
 */
public class MainAdapter extends
        RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static String TAG = "MainAdapter";

    private List<LogContent> logContents = new ArrayList<>();
    private View mEmptyView;

    private Action2<LogContent, Integer> logItemClickListener;

    public void setEmptyView(View emptyView) {
        this.mEmptyView = emptyView;
    }

    public void setLogContents(List<LogContent> logContents) {
        if (logContents != null) {
            this.logContents = logContents;
            notifyDataSetChanged();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_log,
                        parent,
                        false);
        ItemLogBinding binding = DataBindingUtil.bind(itemView);
        return new LogItemHolder(itemView, binding);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ((LogItemHolder) holder).binding
                .setLogContent(logContents.get(position));
        ((LogItemHolder) holder).binding
                .executePendingBindings();
        int colorRes;
        switch (logContents.get(position).getContentType()) {
            case LogContent.TYPE_REQUIREMENT:
                colorRes = R.color.requirement_color;
                break;
            case LogContent.TYPE_BUG:
                colorRes = R.color.bug_color;
                break;
            case LogContent.TYPE_VERSION:
                colorRes = R.color.version_color;
                break;
            case LogContent.TYPE_ERROR:
                colorRes = R.color.other_color;
                break;
            default:
                colorRes = R.color.other_color;
        }
        ((LogItemHolder) holder).mImgTag.setBackgroundColor(holder.itemView.getResources().getColor(colorRes));
        ((LogItemHolder) holder).editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (logItemClickListener != null) {
                    logItemClickListener.onAction(logContents.get(holder.getAdapterPosition()), holder.getAdapterPosition());
                }
            }
        });
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

    public void addLogContent(LogContent logContent) {
        logContents.add(logContent);
        notifyItemInserted(logContents.size() - 1);
    }

    public void updateLogContent(int position, LogContent logContent) {
        logContents.set(position, logContent);
        notifyItemChanged(position);
    }

    public void deleteLogContent(int position) {
        logContents.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * 删除所有的日志
     */
    public void deleteAllLogs() {
        logContents.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return logContents.get(position).getContentType();
    }

    /**
     * 对应布局：R.layout.item_log
     */
    private class LogItemHolder extends RecyclerView.ViewHolder {
        private CheckBox checkBox;
        private ImageView editButton;
        private ImageView mImgTag;

        private ItemLogBinding binding;

        public LogItemHolder(View itemView,
                             ItemLogBinding binding) {
            super(itemView);
            this.binding = binding;
            checkBox = binding.cbSelectItemRequirement;
            editButton = binding.imgEditItemRequirement;
            mImgTag = binding.imgItemLogFlag;
        }
    }

    public void setLogItemClickListener(Action2<LogContent, Integer> logItemClickListener) {
        this.logItemClickListener = logItemClickListener;
    }
}
