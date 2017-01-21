package com.juhezi.coderslife.function.add_log;

import android.content.Context;

import com.juhezi.coderslife.BaseViewModel;
import com.juhezi.coderslife.databinding.ActAddLogBinding;
import com.juhezi.coderslife.entry.LogContent;
import com.juhezi.coderslife.entry.Title;
import com.juhezi.coderslife.tools.Action1;

/**
 * Created by qiao1 on 2017/1/18.
 */
public class AddLogViewModel extends
        BaseViewModel<ActAddLogBinding> {
    private static String TAG = "AddLogViewModel";

    public AddLogViewModel(ActAddLogBinding binding, Context context) {
        super(binding, context);
    }

    @Override
    public void initData() {
        binding.setTitle(new Title(Title.ADD));
        binding.setLogContent(
                new LogContent("", LogContent.TYPE_REQUIREMENT));
    }

    protected void submitLogContent(@LogContent.CONTENT_TYPE int contentType, Action1<Integer> action) {
        LogContent logContent = binding.getLogContent();
        logContent.setContentType(contentType);
        mResponse.addLogContent(logContent, action);
    }

}
