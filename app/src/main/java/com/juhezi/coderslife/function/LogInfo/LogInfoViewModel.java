package com.juhezi.coderslife.function.LogInfo;

import android.content.Context;
import android.util.Log;

import com.juhezi.coderslife.BaseViewModel;
import com.juhezi.coderslife.databinding.ActLogInfoBinding;
import com.juhezi.coderslife.entry.LogContent;

/**
 * Created by qiao1 on 2017/1/22.
 */
public class LogInfoViewModel extends BaseViewModel<ActLogInfoBinding> {
    private static String TAG = "LogInfoViewModel";

    private LogContent logContent;

    public LogInfoViewModel(ActLogInfoBinding binding, Context context, LogContent logContent) {
        super(binding, context);

        binding.setLogContent(logContent);

    }

    @Override
    protected void initData() {

    }

}
