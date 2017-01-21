package com.juhezi.coderslife.function.add_log;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import com.juhezi.coderslife.R;
import com.juhezi.coderslife.databinding.ItemLogTypeBinding;
import com.juhezi.coderslife.entry.LogTypeEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiao1 on 2017/1/21.
 */
public class LogTypeSpinnerAdapter extends BaseAdapter {

    private List<LogTypeEntry> list = new ArrayList<>();
    private ItemLogTypeBinding binding;

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_log_type, parent, false);
        binding = ItemLogTypeBinding.bind(itemView);
        binding.setLogTypeEntry(list.get(position));
        binding.executePendingBindings();
        if (list.get(position).getType() == LogTypeEntry.TYPE_RES) {    //资源图片
            binding.imgItemLogTypeIcon.setImageResource(list.get(position).getResId());
        } else {    //网络图片

        }
        return itemView;
    }

    public void setList(List<LogTypeEntry> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}
