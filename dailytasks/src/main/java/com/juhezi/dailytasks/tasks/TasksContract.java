package com.juhezi.dailytasks.tasks;

import com.juhezi.module.base.mvp.BasePresenter;
import com.juhezi.module.base.mvp.BaseView;

/**
 * Created by Juhezi[juhezix@163.com] on 2017/5/24.
 */

public interface TasksContract {

    interface Presenter extends BasePresenter {
    }

    interface View extends BaseView<Presenter> {

    }

}
