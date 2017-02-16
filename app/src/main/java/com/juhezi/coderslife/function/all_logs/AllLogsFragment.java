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

import com.juhezi.coderslife.R;
import com.juhezi.coderslife.databinding.FragAllLogsBinding;
import com.juhezi.coderslife.entry.LogContent;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_all_logs, container, false);
        FragAllLogsBinding binding = DataBindingUtil.bind(view);
        viewModel = new AllLogsViewModel(binding, getContext());
        mEmptyView = view.findViewById(R.id.view_empty);
        initView(binding);
        initRecyclerView(binding);
        initData(null);
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
                initData(new Action() {
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
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 5) {
                    onScrollDown();
                }
                if (dy < -5) {
                    onScrollUp();
                }
            }
        });
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            //设置滑动类型的标记。需要设置两种类型的flag，即dragFlags 和 swipeFlags，
            // 分别代表着拖拽标记和滑动标记。
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                if (!(viewHolder instanceof LogViewHolder))
                    return 0;   //0代表不滑动
                int swipeFlag = ItemTouchHelper.LEFT;
                int dragFlag = ItemTouchHelper.DOWN | ItemTouchHelper.UP;
                return makeMovementFlags(dragFlag, swipeFlag);
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

    private void onScrollUp() {
        mActionBar.show();
    }

    private void onScrollDown() {
        mActionBar.hide();
    }

    private void initData(final Action action) {
        viewModel.getAllLogs(new Action1<List<LogContent>>() {
            @Override
            public void onAction(final List<LogContent> logContents) {
                //对数据进行处理
                final List<Visitable> data = insertTime(logContents);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.setDatas(data);
                        if (action != null) action.onAction();
                    }
                });
            }
        });
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

}
