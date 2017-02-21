package com.juhezi.coderslife.function.all_logs;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.juhezi.coderslife.R;
import com.juhezi.coderslife.databinding.FragAllLogsBinding;
import com.juhezi.coderslife.entry.LogContent;
import com.juhezi.coderslife.function.all_logs.bean.ProgressBean;
import com.juhezi.coderslife.function.all_logs.bean.TimeBean;
import com.juhezi.coderslife.function.all_logs.view_holder.LogViewHolder;
import com.juhezi.coderslife.multitype.decorate.Visitable;
import com.juhezi.coderslife.tools.Action;
import com.juhezi.coderslife.tools.Action1;
import com.juhezi.coderslife.tools.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiao1 on 2017/1/25.
 */
public class AllLogsFragment extends Fragment {
    private static String TAG = "AllLogsFragment";

    private AllLogsViewModel viewModel;
    private Toolbar mToolbar;
    private ActionBar mActionBar;
    private AppCompatActivity activity;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private AllLogsAdapter mAdapter;
    private View mEmptyView;
    private boolean hasShown = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_all_logs, container, false);
        FragAllLogsBinding binding = DataBindingUtil.bind(view);
        viewModel = new AllLogsViewModel(binding, getContext());
        mEmptyView = view.findViewById(R.id.view_empty);
        initView(binding);
        initRecyclerView(binding);
        getDatas(null);
        return view;
    }

    private void initView(FragAllLogsBinding binding) {
        mToolbar = binding.tbFragAllLogs;
        activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(mToolbar);
        mActionBar = activity.getSupportActionBar();
        mActionBar.setHomeButtonEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mSwipeRefreshLayout = binding.srlRefresh;
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.clearData();
                viewModel.setStart(0);  //重新开始加载
                getDatas(new Action() {
                    @Override
                    public void onAction() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });
    }

    private void initRecyclerView(FragAllLogsBinding binding) {
        mRecyclerView = binding.rvListFragAllLogs;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new AllLogsAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setEmptyView(mEmptyView);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

            int transY = 0;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                transY = dy;
                if (dy > 5) {
                    onScrollDown();
                }
                if (dy < -5) {
                    onScrollUp();
                }
                if (((LinearLayoutManager) recyclerView.getLayoutManager())
                        .findLastVisibleItemPosition() == mAdapter.getItemCount() - 1 && !hasShown &&
                        recyclerView.getLayoutManager().getChildCount() < mAdapter.getItemCount()) {
                    hasShown = true;
                    showToast(getString(R.string.drag_up_load_more));
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    if (((LinearLayoutManager) recyclerView.getLayoutManager())
                            .findLastVisibleItemPosition() == mAdapter.getItemCount() - 1) {
                        if (transY > 0) {   //向上滑动
                            if (mAdapter.addProgressBar()) {    //添加了Progressbar
                                getDatas(null);
                            }
                        }
                    }
                }
            }
        });
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                if (!(viewHolder instanceof LogViewHolder))
                    return 0;   //0代表不滑动
                int swipeFlag = ItemTouchHelper.LEFT;
                return makeMovementFlags(0, swipeFlag);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                if (direction == ItemTouchHelper.LEFT) {
                    int position = viewHolder.getAdapterPosition();
                    LogContent log = mAdapter.removeLogItem(position);
                    if (log != null) {
                        viewModel.removeLog(log.getId(), new Action1<Integer>() {
                            @Override
                            public void onAction(final Integer integer) {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (integer == Config.RESULT_CODE_OK)
                                            showSnackbar(getString(R.string.remove_success));
                                        else
                                            showSnackbar(getString(R.string.remove_fail));
                                    }
                                });
                            }
                        });
                    } else {
                        showSnackbar(getString(R.string.remove_fail));
                    }
                }
            }
        });
        helper.attachToRecyclerView(mRecyclerView);
    }

    /**
     * 分页加载数据
     */
    private void getDatas(final Action action) {
        viewModel.getPartLogs(new Action1<List<LogContent>>() {
            @Override
            public void onAction(final List<LogContent> logContents) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.removeProgressBar();
                        if (logContents.size() == 0) {
                            showToast(getString(R.string.have_no_logs));
                        } else {
                            mAdapter.addDatas(insertTime(logContents));
                        }
                        if (action != null) action.onAction();
                    }
                });
            }
        });
    }

    private void onScrollUp() {
        mActionBar.show();
    }

    private void onScrollDown() {
        mActionBar.hide();
    }

    private static AllLogsFragment sInstance;

    public static AllLogsFragment getInstance() {
        if (sInstance == null) {
            sInstance = new AllLogsFragment();
        }
        return sInstance;
    }

    /**
     * 插入时间
     *
     * @param logs
     * @return
     */
    private List<Visitable> insertTime(List<LogContent> logs) {
        List<Visitable> data = new ArrayList(logs);
        String time = "";
        String temp = "";
        int num = 0;    //插入的次数
        for (int i = 0; i < logs.size(); i++) {
            temp = logs.get(i).getTime();
            if (!temp.equals(time)) {    //时间不相等
                data.add(i + num, new TimeBean(temp));
                num++;
                time = temp;
            }
        }
        return data;
    }

    private void showSnackbar(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }

    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

}
