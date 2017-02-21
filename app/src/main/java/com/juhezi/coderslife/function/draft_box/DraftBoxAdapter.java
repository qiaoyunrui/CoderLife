package com.juhezi.coderslife.function.draft_box;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.juhezi.coderslife.function.draft_box.bean.LogDraftBean;
import com.juhezi.coderslife.function.draft_box.view_holder.LogDraftHolder;
import com.juhezi.coderslife.multitype.decorate.Visitable;
import com.juhezi.coderslife.multitype.factory.ItemTypefactory;
import com.juhezi.coderslife.multitype.factory.TypeFactory;
import com.juhezi.coderslife.multitype.viewholder.BaseViewHolder;

import java.util.List;

/**
 * Created by qiao1 on 2017/2/20.
 */
public class DraftBoxAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static String TAG = "DraftBoxAdapter";

    private List<Visitable> datas;
    private View emptyView;
    private TypeFactory factory;

    public DraftBoxAdapter() {
        factory = new ItemTypefactory(this);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(viewType, parent, false);
        return factory.createViewHolder(viewType, itemView);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.bindViewData(datas.get(position), position);
        setClickListener(holder);
    }

    @Override
    public int getItemCount() {
        if (datas == null) {
            emptyView.setVisibility(View.VISIBLE);
            return 0;
        }
        if (emptyView != null) {
            if (datas.size() == 0) {
                emptyView.setVisibility(View.VISIBLE);
            } else {
                emptyView.setVisibility(View.INVISIBLE);
            }
        }
        return datas.size();
    }

    @Override
    public int getItemViewType(int position) {
        return datas.get(position).type(factory);
    }

    public void setEmptyView(View emptyView) {
        this.emptyView = emptyView;
    }

    public void setDatas(List<Visitable> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void removeData(int position) {
        datas.remove(position);
        notifyItemRemoved(position);
    }

    public void removeAllData() {
        datas.clear();
        notifyDataSetChanged();
    }

    private void setClickListener(BaseViewHolder holder) {
        if (holder instanceof LogDraftHolder) {
            ((LogDraftHolder) holder).setListener(draftItemClickListener);
        }
    }

    private LogDraftHolder.onClickListener draftItemClickListener;

    public void setDraftItemClickListener(LogDraftHolder.onClickListener listener) {
        this.draftItemClickListener = listener;
    }

    public LogDraftBean getDraft(int position) {
        if (datas == null || datas.size() <= position)
            return null;
        Visitable visitable = datas.get(position);
        if (!(visitable instanceof LogDraftBean))
            return null;
        return (LogDraftBean) visitable;
    }

}
