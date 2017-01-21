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
        switch (viewType) {
            case LogContent.TYPE_REQUIREMENT:
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_log,
                                parent,
                                false);
                ItemLogBinding binding = DataBindingUtil.bind(itemView);
                return new RequirementItemHolder
                        (itemView, binding);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case LogContent.TYPE_REQUIREMENT:
                Log.i(TAG, "onBindViewHolder: " + ((RequirementItemHolder) holder).binding);
                ((RequirementItemHolder) holder).binding
                        .setLogContent(logContents.get(position));
                ((RequirementItemHolder) holder).binding
                        .executePendingBindings();
                break;
        }
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

    @Override
    public int getItemViewType(int position) {
        return logContents.get(position).getContentType();
    }

    /**
     * 对应布局：R.layout.item_log
     */
    private class RequirementItemHolder extends RecyclerView.ViewHolder {

        private CheckBox checkBox;
        private ImageView editButton;
        private ItemLogBinding binding;

        public RequirementItemHolder(View itemView,
                                     ItemLogBinding binding) {
            super(itemView);
            this.binding = binding;
            checkBox = binding.cbSelectItemRequirement;
            editButton = binding.imgEditItemRequirement;
        }
    }

    public void addLogContent(LogContent logContent) {
        logContents.add(logContent);
        notifyItemInserted(logContents.size() - 1);
    }

}
