package com.juhezi.coderslife.function.all_logs;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.juhezi.coderslife.R;
import com.juhezi.coderslife.databinding.ItemAllLogsBinding;
import com.juhezi.coderslife.entry.LogContent;
import com.juhezi.coderslife.multitype.decorate.Visitable;
import com.juhezi.coderslife.multitype.factory.TypeFactory;
import com.juhezi.coderslife.multitype.viewholder.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiao1 on 2017/1/25.
 */
public class AllLogsAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static String TAG = "AllLogsAdapter";

    private List<Visitable> datas;
    private TypeFactory typeFactory;

    private View mEmptyView;

    public AllLogsAdapter() {
        typeFactory = new ItemTypefactory(this);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(viewType, parent, false);
        return typeFactory.createViewHolder(viewType, itemView);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.bindViewData(datas.get(position), position);
    }

    @Override
    public int getItemCount() {
        if (datas == null) {
            return 0;
        }
        if (mEmptyView != null) {
            if (datas.size() == 0) {
                mEmptyView.setVisibility(View.VISIBLE);
            } else {
                mEmptyView.setVisibility(View.INVISIBLE);
            }
        }
        return datas.size();
    }

    /**
     * 设置数据
     */
    public void setDatas(List<Visitable> datas) {
        this.datas = datas;
    }

    public void addDatas(Visitable data) {
        if (datas == null) {
            datas = new ArrayList<>();
        }
        datas.add(data);
    }

    @Override
    public int getItemViewType(int position) {
        return datas.get(position).type(typeFactory);
    }

    public void setEmptyView(View emptyView) {
        this.mEmptyView = emptyView;
    }
}
