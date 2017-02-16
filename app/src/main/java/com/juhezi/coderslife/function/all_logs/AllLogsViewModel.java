package com.juhezi.coderslife.function.all_logs;

import android.content.Context;

import com.juhezi.coderslife.BaseViewModel;
import com.juhezi.coderslife.databinding.FragAllLogsBinding;
import com.juhezi.coderslife.entry.LogContent;
import com.juhezi.coderslife.entry.Title;
import com.juhezi.coderslife.tools.Action1;

import java.util.List;

/**
 * Created by qiao1 on 2017/1/25.
 */
public class AllLogsViewModel extends BaseViewModel<FragAllLogsBinding> {
    private static String TAG = "AllLogsViewModel";

    public AllLogsViewModel(FragAllLogsBinding binding, Context context) {
        super(binding, context);
    }

    @Override
    protected void initData() {
        binding.setTitle(new Title(Title.ALL_LOGS));
    }

    protected void getAllLogs(Action1<List<LogContent>> action) {
        mResponse.getAllLogs(action);
    }

    public void removeLog(String id, Action1<Integer> action) {
        mResponse.removeLog(id, action);
    }

}
