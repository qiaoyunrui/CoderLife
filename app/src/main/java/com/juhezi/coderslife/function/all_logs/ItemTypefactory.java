package com.juhezi.coderslife.function.all_logs;

import android.databinding.DataBindingUtil;
import android.support.annotation.IntDef;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.juhezi.coderslife.R;
import com.juhezi.coderslife.databinding.ItemAllLogsBinding;
import com.juhezi.coderslife.databinding.ItemAllLogsProgressBinding;
import com.juhezi.coderslife.databinding.ItemAllLogsTimeBinding;
import com.juhezi.coderslife.entry.LogContent;
import com.juhezi.coderslife.function.all_logs.bean.ProgressBean;
import com.juhezi.coderslife.function.all_logs.bean.TimeBean;
import com.juhezi.coderslife.function.all_logs.view_holder.LogViewHolder;
import com.juhezi.coderslife.function.all_logs.view_holder.ProgressViewHolder;
import com.juhezi.coderslife.function.all_logs.view_holder.TimeViewHolder;
import com.juhezi.coderslife.multitype.factory.TypeFactory;
import com.juhezi.coderslife.multitype.viewholder.BaseViewHolder;

/**
 * Created by qiao1 on 2017/2/16.
 */
public class ItemTypefactory extends TypeFactory {
    private static String TAG = "ItemTypefactory";

    @LayoutRes
    public static final int LOG_ITEM_LAYOUT = R.layout.item_all_logs;   //Log Item
    @LayoutRes
    public static final int TIME_ITEM_LAYOUT = R.layout.item_all_logs_time; //Time item

    public static final int PROGRESS_ITEM_LAYOUT = R.layout.item_all_logs_progress;     //Progress item

    @IntDef({LOG_ITEM_LAYOUT, TIME_ITEM_LAYOUT, PROGRESS_ITEM_LAYOUT})
    public @interface ItemLayout {
    }

    public ItemTypefactory(RecyclerView.Adapter adapter) {
        super(adapter);
    }

    @ItemLayout
    @Override
    public int type(LogContent logEntry) {
        return LOG_ITEM_LAYOUT;
    }

    @ItemLayout
    @Override
    public int type(TimeBean timeBean) {
        return TIME_ITEM_LAYOUT;
    }

    @ItemLayout
    @Override
    public int type(ProgressBean progressBean) {
        return PROGRESS_ITEM_LAYOUT;
    }

    @Override
    public BaseViewHolder createViewHolder(int type, View itemView) {
        switch (type) {
            case LOG_ITEM_LAYOUT:
                return new LogViewHolder(itemView, (ItemAllLogsBinding) DataBindingUtil.bind(itemView), adapter);
            case TIME_ITEM_LAYOUT:
                return new TimeViewHolder(itemView, (ItemAllLogsTimeBinding) DataBindingUtil.bind(itemView), adapter);
            case PROGRESS_ITEM_LAYOUT:
                return new ProgressViewHolder(itemView, (ItemAllLogsProgressBinding) DataBindingUtil.bind(itemView), adapter);
            default:
                return null;
        }
    }
}
