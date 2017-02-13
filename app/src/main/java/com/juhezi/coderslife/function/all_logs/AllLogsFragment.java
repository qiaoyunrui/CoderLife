package com.juhezi.coderslife.function.all_logs;

import android.animation.ObjectAnimator;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.juhezi.coderslife.R;
import com.juhezi.coderslife.databinding.FragAllLogsBinding;
import com.juhezi.coderslife.entry.LogContent;
import com.juhezi.coderslife.tools.Action1;

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
        initData();
        return view;
    }

    private void initView(FragAllLogsBinding binding) {
        mToolbar = binding.tbFragAllLogs;
        activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(mToolbar);
        mActionBar = activity.getSupportActionBar();
        mActionBar.setHomeButtonEnabled(true);
        mActionBar.setDisplayHomeAsUpEnabled(true);
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
                int position = viewHolder.getAdapterPosition();
                if(direction == ItemTouchHelper.LEFT) {
                    // TODO: 2017/2/13 删除数据，显示Toast，提供撤销操作
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

    private void initData() {
        viewModel.getAllLogs(new Action1<List<LogContent>>() {
            @Override
            public void onAction(final List<LogContent> logContents) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.setLogContents(logContents);
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

}
