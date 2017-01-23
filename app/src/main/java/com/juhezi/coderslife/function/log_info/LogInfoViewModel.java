package com.juhezi.coderslife.function.log_info;

import android.content.Context;
import android.util.Log;

import com.juhezi.coderslife.BaseViewModel;
import com.juhezi.coderslife.databinding.ActLogInfoBinding;
import com.juhezi.coderslife.entry.LogContent;
import com.juhezi.coderslife.tools.Action1;

/**
 * Created by qiao1 on 2017/1/22.
 */
public class LogInfoViewModel extends BaseViewModel<ActLogInfoBinding> {
    private static String TAG = "LogInfoViewModel";

    public LogInfoViewModel(ActLogInfoBinding binding, Context context, LogContent logContent) {
        super(binding, context);
        binding.setLogContent(logContent);
    }

    @Override
    protected void initData() {

    }

    protected void save(Action1<Integer> action1) {
        mResponse.updateLog(binding.getLogContent(), action1);
    }

    protected LogContent getLogContent() {
        return binding.getLogContent();
    }

    protected void delete(Action1<Integer> action1) {
        mResponse.removeLog(binding.getLogContent().getId(), action1);
    }

}
