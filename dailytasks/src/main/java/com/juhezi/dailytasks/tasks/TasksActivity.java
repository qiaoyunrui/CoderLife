package com.juhezi.dailytasks.tasks;

import android.support.v4.app.Fragment;

import com.juhezi.dailytasks.R;
import com.juhezi.module.base.SingleFragmentActivity;

public class TasksActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return null;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_tasks;
    }

    @Override
    protected int getFragmentWrapperId() {
        return 0;
    }
}
