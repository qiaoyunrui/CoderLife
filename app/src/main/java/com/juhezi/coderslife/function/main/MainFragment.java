package com.juhezi.coderslife.function.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.juhezi.coderslife.R;
import com.juhezi.coderslife.databinding.FragMainBinding;
import com.juhezi.coderslife.entry.LogContent;
import com.juhezi.coderslife.function.add_requirement.AddRequirementActivity;
import com.juhezi.coderslife.model.Response;
import com.juhezi.coderslife.model.ResponseImpl;
import com.juhezi.coderslife.tools.Action;
import com.juhezi.coderslife.tools.Action1;
import com.juhezi.coderslife.tools.Config;
import com.konifar.fab_transformation.FabTransformation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by qiao1 on 2017/1/18.
 */
public class MainFragment extends Fragment {
    private static String TAG = "MainFragment";

    public static MainFragment getInstance() {
        return Holder.sInstance;
    }

    private static final class Holder {
        private static final MainFragment sInstance = new MainFragment();
    }

    public MainFragment() {
    }

    private FragMainBinding binding;

    private FloatingActionButton mFabAdd;
    private CardView mCvNavWrapper;
    private NavigationView mNavAdd;
    private View mVOverlay;
    private RecyclerView mRvList;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private Response response;

    private boolean isItemOpen = false;
    private MainAdapter mAdapter = new MainAdapter();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_main, container, false);
        binding = FragMainBinding.bind(rootView);
        response = ResponseImpl.getInstance(getContext());
        initEvent();
        initRecyclerView();
        initData();
        return rootView;
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mSwipeRefreshLayout.setRefreshing(true);
        showTodayAllLogs(new Action() {
            @Override
            public void onAction() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }


    /**
     * 显示今日所有日志
     *
     * @param action
     */
    private void showTodayAllLogs(final Action action) {
        response.getTodayAllLogs(getTodayTime(), new Action1<List<LogContent>>() {
            @Override
            public void onAction(final List<LogContent> logContents) {
                MainFragment.this.getActivity().runOnUiThread(new Runnable() {  //在UI线程执行
                    @Override
                    public void run() {
                        mAdapter.setLogContents(logContents);
                        action.onAction();
                    }
                });
            }
        });
    }

    private String getTodayTime() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());  //获取当前时间
    }

    private void initEvent() {
        mFabAdd = binding.fabAdd;
        mNavAdd = binding.navFragMainAddItem;
        mCvNavWrapper = binding.cvFragMainAddItemWrapper;
        mVOverlay = binding.vFragMainOverlay;
        mRvList = binding.rvListFragMain;
        mSwipeRefreshLayout = binding.srlRefreshFragMain;
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        mFabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openItem();
            }
        });
        mVOverlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeItem();
            }
        });
        mNavAdd.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                closeItem();
                switch (item.getItemId()) {
                    case R.id.item_nav_add_main_requirement:
                        openAddRequirementAct();
                }
                return false;
            }
        });
        //下拉刷新数据
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showTodayAllLogs(new Action() {
                    @Override
                    public void onAction() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });
    }

    private void initRecyclerView() {
        mRvList.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvList.setAdapter(mAdapter);
    }

    private void openItem() {
        FabTransformation.with(mFabAdd)
                .setOverlay(mVOverlay)
                .transformTo(mCvNavWrapper);
        isItemOpen = true;
    }

    public void closeItem() {
        FabTransformation.with(mFabAdd)
                .setOverlay(mVOverlay)
                .transformFrom(mCvNavWrapper);
        isItemOpen = false;
    }

    private void openAddRequirementAct() {
        Intent addRequIntent = new Intent(getContext(),
                AddRequirementActivity.class);
        startActivityForResult(addRequIntent, Config.TAG_MAIN_FRAGMENT_TO_ADD_REQUIREMENT);
    }

    public boolean isItemOpen() {
        return isItemOpen;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Config.TAG_MAIN_FRAGMENT_TO_ADD_REQUIREMENT) {

            if (resultCode == Config.TAG_ADD_REQUIREMENT_RETURN) {
                if (data != null) {
                    LogContent logContent = (com.juhezi.coderslife.entry.LogContent)
                            data.getSerializableExtra(Config.ADD_REQUIREMENT_LOG_CONTENT);
                    mAdapter.addLogContent(logContent);
                }
            }
        }
    }
}
