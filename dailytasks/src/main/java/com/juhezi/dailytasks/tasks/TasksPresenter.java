package com.juhezi.dailytasks.tasks;

/**
 * Created by Juhezi[juhezix@163.com] on 2017/5/24.
 */

public class TasksPresenter implements TasksContract.Presenter {

    private TasksContract.View mView;

    public TasksPresenter(TasksContract.View view) {
        this.mView = view;
        view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void destroy() {

    }
}
