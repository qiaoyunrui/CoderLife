package com.juhezi.dailytasks.tasks;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.juhezi.dailytasks.R;
import com.juhezi.module.base.common.SingleFragmentActivity;
import com.juhezi.module.base.router.JUri;

public class TasksActivity extends SingleFragmentActivity {

    public static final JUri URL = new JUri(JUri.ACTIVITY, TasksActivity.class.getName());

    @Override
    protected Fragment createFragment() {
        return TasksFragment.newInsatnce();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_tasks;
    }

    @Override
    protected int getFragmentWrapperId() {
        return R.id.tasks_fragment_wrapper;
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        super.init(savedInstanceState);
    }
}
