package com.juhezi.dailytasks;

import com.juhezi.dailytasks.tasks.TasksActivity;
import com.juhezi.module.base.common.BaseApplication;
import com.juhezi.module.base.router.operator.manager.OMP;

/**
 * Created by Juhezi[juhezix@163.com] on 2017/6/5.
 */

public class DailyTasksApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        OMP.putURI(TasksActivity.URL, TasksActivity.class);
    }
}
