package com.juhezi.dailytasks.tasks;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.juhezi.dailytasks.R;
import com.juhezi.module.base.common.BaseFragment;
import com.juhezi.module.base.util.TypefaceBuilder;

/**
 * Created by Juhezi[juhezix@163.com] on 2017/5/24.
 */

public class TasksFragment extends BaseFragment implements TasksContract.View {

    private TasksContract.Presenter mPresenter;
    private Toolbar mToolbar;
    private ActionBar mActionBar;

    @Override
    public void setPresenter(TasksContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public static TasksFragment newInsatnce() {
        return new TasksFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_tasks;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new TasksPresenter(this);
    }

    @Override
    protected void init() {
        mToolbar = (Toolbar) rootView.findViewById(R.id.tasks_fragment_tb);
        getAppCompatActivity().setSupportActionBar(mToolbar);
        mActionBar = getAppCompatActivity().getSupportActionBar();
        TextView textView = (TextView) rootView.findViewById(R.id.tv_test);
        textView.setTypeface(TypefaceBuilder.build());
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mPresenter != null) {
            mPresenter.start();
        }
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.destroy();
        }
        super.onDestroy();
    }
}
