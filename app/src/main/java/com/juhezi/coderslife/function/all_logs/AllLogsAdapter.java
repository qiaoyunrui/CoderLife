package com.juhezi.coderslife.function.all_logs;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.juhezi.coderslife.R;
import com.juhezi.coderslife.databinding.ItemAllLogsBinding;
import com.juhezi.coderslife.entry.LogContent;
import com.juhezi.coderslife.function.all_logs.bean.ProgressBean;
import com.juhezi.coderslife.function.all_logs.bean.TimeBean;
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
            mEmptyView.setVisibility(View.VISIBLE);
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

    /**
     * 添加数据
     *
     * @param datas
     */
    public void addDatas(List<Visitable> datas) {
        if (this.datas == null) {
            this.datas = new ArrayList<Visitable>();
        }
        if (datas != null || datas.size() != 0) {
            if (this.datas.size() != 0 &&
                    ((LogContent) this.datas.get(this.datas.size() - 1)).getTime()
                            .equals(((TimeBean) datas.get(0)).getTime())) {
                datas.remove(0);
            }
            this.datas.addAll(datas);
        }
        notifyDataSetChanged();
    }

    public void clearData() {
        if (datas != null) {
            datas.clear();
        }
    }

    public void addData(Visitable data) {
        if (datas == null) {
            datas = new ArrayList<>();
        }
        datas.add(data);
        notifyItemInserted(getItemCount() - 1);
    }

    public boolean addProgressBar() {
        if (datas == null) return false;
        if (datas.get(getItemCount() - 1) instanceof ProgressBean) return false;
        addData(new ProgressBean());
        return true;
    }

    public boolean removeProgressBar() {
        if (datas == null) return false;
        if (getItemCount() < 1) return false;
        if (!(datas.get(getItemCount() - 1) instanceof ProgressBean)) return false;
        datas.remove(getItemCount() - 1);
        notifyItemRemoved(getItemCount() - 1);
        return true;
    }

    /**
     * 删除数据Item
     *
     * @param position
     */
    public LogContent removeLogItem(int position) {
        if (position >= getItemCount()) return null;
        LogContent log = (LogContent) datas.get(position);
        boolean isRemoveTime = false;
        if (position > 0 &&
                !(datas.get(position - 1) instanceof LogContent)) {
            if (position + 1 == getItemCount() ||
                    !(datas.get(position + 1) instanceof LogContent)) {
                isRemoveTime = true;
            }
        }
        if (isRemoveTime) {
            //删除time
            datas.remove(position - 1);
            notifyItemRemoved(position - 1);
            //删除log
            datas.remove(position - 1);
            notifyItemRemoved(position - 1);
        } else {
            datas.remove(position);
            notifyItemRemoved(position);
        }
        return log;
    }

    @Override
    public int getItemViewType(int position) {
        return datas.get(position).type(typeFactory);
    }

    public void setEmptyView(View emptyView) {
        this.mEmptyView = emptyView;
    }
}
