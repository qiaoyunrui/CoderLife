package com.juhezi.coderslife.function.draft_box.view_holder;

import android.app.Application;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.juhezi.coderslife.BaseViewModel;
import com.juhezi.coderslife.R;
import com.juhezi.coderslife.databinding.ItemLogDraftBinding;
import com.juhezi.coderslife.function.draft_box.bean.LogDraftBean;
import com.juhezi.coderslife.multitype.viewholder.BaseViewHolder;
import com.juhezi.coderslife.tools.Action;
import com.juhezi.coderslife.tools.Action1;
import com.juhezi.loadablebutton.view.LoadableButton;

/**
 * Created by qiao1 on 2017/2/18.
 */
public class LogDraftHolder extends BaseViewHolder<LogDraftBean, ItemLogDraftBinding> {
    private static String TAG = "LogDraftHolder";

    private LoadableButton loadableButton;

    public LogDraftHolder(View itemView, ItemLogDraftBinding binding, RecyclerView.Adapter adapter) {
        super(itemView, binding, adapter);
        loadableButton = (LoadableButton) getView(R.id.lb_commit);
    }

    @Override
    public void bindViewData(final LogDraftBean data, final int position) {
        binding.setLogDraft(data);
        binding.executePendingBindings();
        if (data.getDraftType() == LogDraftBean.TYPE_DRAFT_ADD) {
            loadableButton.setText("添加");
        } else {
            loadableButton.setText("更新");
        }
        loadableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onLoadableClick(loadableButton, data, position);
                }
            }
        });
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(data, position);
                }
            }
        });
    }

    public void setListener(onClickListener listener) {
        this.listener = listener;
    }

    private onClickListener listener;

    public interface onClickListener {

        void onItemClick(LogDraftBean logDraftBean, int position);

        void onLoadableClick(LoadableButton button, LogDraftBean logDraftBean, int position);
    }

}
