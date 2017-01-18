package com.juhezi.coderslife.function.main;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.juhezi.coderslife.R;
import com.juhezi.coderslife.databinding.ItemRequirementBinding;
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

    public void setLogContents(List<LogContent> logContents) {
        if (logContents != null) {
            this.logContents = logContents;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case LogContent.TYPE_REQUIREMENT:
                View itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_requirement,
                                parent,
                                false);
                ItemRequirementBinding binding = DataBindingUtil.bind(itemView);
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
                ((RequirementItemHolder) holder).binding
                        .setLogContent(logContents.get(position));
                ((RequirementItemHolder) holder).binding
                        .executePendingBindings();
                break;
        }
    }

    @Override
    public int getItemCount() {
        return logContents.size();
    }

    @Override
    public int getItemViewType(int position) {
        return logContents.get(position).getContentType();
    }

    /**
     * 对应布局：R.layout.item_requirement
     */
    private class RequirementItemHolder extends RecyclerView.ViewHolder {

        private CheckBox checkBox;
        private ImageView editButton;
        private ItemRequirementBinding binding;

        public RequirementItemHolder(View itemView,
                                     ItemRequirementBinding binding) {
            super(itemView);
            checkBox = binding.cbSelectItemRequirement;
            editButton = binding.imgEditItemRequirement;
        }
    }

}
