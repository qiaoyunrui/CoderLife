package com.juhezi.coderslife.function.draft_box;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.juhezi.coderslife.R;
import com.juhezi.coderslife.databinding.FragDraftBoxBinding;
import com.juhezi.coderslife.function.draft_box.bean.LogDraftBean;
import com.juhezi.coderslife.function.draft_box.view_holder.LogDraftHolder;
import com.juhezi.coderslife.multitype.decorate.Visitable;
import com.juhezi.coderslife.tools.Action;
import com.juhezi.coderslife.tools.Action1;
import com.juhezi.loadablebutton.view.LoadableButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiao1 on 2017/2/17.
 */
public class DraftBoxFragment extends Fragment {
    private static String TAG = "DraftBoxFragment";

    private static final class Holder {
        private static final DraftBoxFragment sInstance = new DraftBoxFragment();
    }

    public static DraftBoxFragment getInstance() {
        return Holder.sInstance;
    }

    private DraftBoxViewModel viewModel;
    private Toolbar mToolbar;
    private ActionBar mActionBar;
    private DraftBoxActivity activity;
    private View mEmptyView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private DraftBoxAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_draft_box, container, false);
        FragDraftBoxBinding binding = DataBindingUtil.bind(view);
        viewModel = new DraftBoxViewModel(binding, getContext());
        mEmptyView = view.findViewById(R.id.view_empty);
        initView(binding);
        initRecyclerView(binding);
        initData(null);
        return view;
    }

    private void initView(FragDraftBoxBinding binding) {
        mToolbar = binding.tbFragDraftBox;
        activity = (DraftBoxActivity) getActivity();
        activity.setSupportActionBar(mToolbar);
        mActionBar = activity.getSupportActionBar();
        mActionBar.setHomeButtonEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mSwipeRefreshLayout = binding.srlRefreshFragDraftBox;
        mRecyclerView = binding.rvListFragDraftBox;
    }

    private void initRecyclerView(FragDraftBoxBinding binding) {
        adapter = new DraftBoxAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
        adapter.setEmptyView(mEmptyView);
        adapter.setDraftItemClickListener(new LogDraftHolder.onClickListener() {
            @Override
            public void onItemClick(LogDraftBean logDraftBean) {
            }

            @Override
            public void onLoadableClick(LoadableButton loadableButton, LogDraftBean logDraftBean) {
                loadableButton.load();
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData(new Action() {
                    @Override
                    public void onAction() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });
    }

    private void initData(final Action action) {
        viewModel.getDrafts(new Action1<List<LogDraftBean>>() {
            @Override
            public void onAction(final List<LogDraftBean> drafts) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        List<Visitable> datas = new ArrayList<Visitable>(drafts);
                        adapter.setDatas(datas);
                        if (action != null)
                            action.onAction();
                    }
                });
            }
        });
    }
}
