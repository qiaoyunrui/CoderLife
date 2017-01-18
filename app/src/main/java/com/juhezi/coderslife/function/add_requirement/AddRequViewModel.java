package com.juhezi.coderslife.function.add_requirement;

import android.util.Log;

import com.juhezi.coderslife.BaseViewModel;
import com.juhezi.coderslife.databinding.ActAddRequirementBinding;
import com.juhezi.coderslife.entry.LogContent;
import com.juhezi.coderslife.entry.Title;
import com.juhezi.coderslife.tools.Action1;

/**
 * Created by qiao1 on 2017/1/18.
 */
public class AddRequViewModel extends
        BaseViewModel<ActAddRequirementBinding> {
    private static String TAG = "AddRequViewModel";

    public AddRequViewModel(ActAddRequirementBinding binding) {
        super(binding);
    }

    @Override
    public void initData() {
        binding.setTitle(new Title(Title.ADD_REQUIREMENT));
        binding.setLogContent(
                new LogContent("", LogContent.TYPE_REQUIREMENT));
    }

    protected void submitLogContent(Action1<Integer> action) {
        Log.i(TAG, "[" + binding.getLogContent().getContent() + "]");
    }

}
