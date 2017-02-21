package com.juhezi.coderslife.function.draft_box;

import android.content.Intent;
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
import android.widget.Toast;

import com.juhezi.coderslife.R;
import com.juhezi.coderslife.databinding.FragDraftBoxBinding;
import com.juhezi.coderslife.entry.LogContent;
import com.juhezi.coderslife.function.add_log.AddLogActivity;
import com.juhezi.coderslife.function.draft_box.bean.LogDraftBean;
import com.juhezi.coderslife.function.draft_box.view_holder.LogDraftHolder;
import com.juhezi.coderslife.function.log_info.LogInfoActivity;
import com.juhezi.coderslife.multitype.decorate.Visitable;
import com.juhezi.coderslife.tools.Action;
import com.juhezi.coderslife.tools.Action1;
import com.juhezi.coderslife.tools.Config;
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
    private int position = -1;
    private String id;

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
            public void onItemClick(LogDraftBean logDraftBean, int position) {
                if (logDraftBean.getDraftType() == LogDraftBean.TYPE_DRAFT_ADD) {
                    turn2AddLogAct(logDraftBean.getLog());
                } else {
                    turn2EditLogAct(logDraftBean.getLog());
                }
                DraftBoxFragment.this.position = position;
                id = logDraftBean.getId();
            }

            @Override
            public void onLoadableClick(final LoadableButton loadableButton, final LogDraftBean logDraftBean, final int position) {
                loadableButton.load();
                if (logDraftBean.getDraftType() == LogDraftBean.TYPE_DRAFT_ADD) {
                    viewModel.addLog(logDraftBean.getLog(), new Action1<Integer>() {
                        @Override
                        public void onAction(final Integer integer) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (integer == Config.RESULT_CODE_OK) {
                                        viewModel.removeDraft(logDraftBean.getId());
                                        showToast(getString(R.string.save_change_success));
                                        adapter.removeData(position);
                                    } else {
                                        showToast(getString(R.string.save_change_fail));
                                        loadableButton.unLoad();
                                    }
                                }
                            });

                        }
                    });
                } else {
                    viewModel.update(logDraftBean.getLog(), new Action1<Integer>() {
                        @Override
                        public void onAction(final Integer integer) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (integer == Config.RESULT_CODE_OK) {
                                        viewModel.removeDraft(logDraftBean.getId());
                                        showToast(getString(R.string.save_change_success));
                                        adapter.removeData(position);
                                    } else {
                                        showToast(getString(R.string.save_change_fail));
                                        loadableButton.unLoad();
                                    }
                                }
                            });
                        }
                    });
                }
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

    private void turn2EditLogAct(LogContent log) {
        Intent intent = new Intent(getContext(), LogInfoActivity.class);
        intent.putExtra(Config.SHOW_LOG_CONTENT_INFO, log);
        startActivityForResult(intent, Config.TAG_DRAFT_FRAGMENT_TO_LOG_INFO_ACT);
    }

    private void turn2AddLogAct(LogContent log) {
        Intent intent = new Intent(getContext(), AddLogActivity.class);
        intent.putExtra(Config.ADD_REQUIREMENT_LOG_CONTENT, log);
        startActivityForResult(intent, Config.TAG_DRAFT_FRAGMENT_TO_ADD_REQUIREMENT);
    }

    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (position < 0) return;
        if ((requestCode == Config.TAG_DRAFT_FRAGMENT_TO_ADD_REQUIREMENT
                && resultCode == Config.TAG_ADD_REQUIREMENT_RETURN) ||
                (requestCode == Config.TAG_DRAFT_FRAGMENT_TO_LOG_INFO_ACT
                        && (resultCode == Config.TAG_LOG_INFO_RETURN_SAVE ||
                        resultCode == Config.TAG_LOG_INFO_RETURN_DELETE))) {
            adapter.removeData(position);
            viewModel.removeDraft(id);
        }
    }
}
