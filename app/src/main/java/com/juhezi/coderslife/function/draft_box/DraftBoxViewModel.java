package com.juhezi.coderslife.function.draft_box;

import android.content.Context;

import com.juhezi.coderslife.BaseViewModel;
import com.juhezi.coderslife.databinding.FragDraftBoxBinding;
import com.juhezi.coderslife.entry.LogContent;
import com.juhezi.coderslife.entry.Title;
import com.juhezi.coderslife.function.draft_box.bean.LogDraftBean;
import com.juhezi.coderslife.tools.Action;
import com.juhezi.coderslife.tools.Action1;

import java.util.List;

/**
 * Created by qiao1 on 2017/2/17.
 */
public class DraftBoxViewModel extends BaseViewModel<FragDraftBoxBinding> {
    private static String TAG = "DraftBoxViewModel";

    public DraftBoxViewModel(FragDraftBoxBinding binding, Context context) {
        super(binding, context);
    }

    @Override
    protected void initData() {
        binding.setTitle(new Title(Title.DRAFT_BOX));
    }

    void getDrafts(Action1<List<LogDraftBean>> action) {
        mResponse.getDrafts(action);
    }

    void addLog(LogContent log, Action1<Integer> action) {
        mResponse.addLogContent(log, action);
    }

    void update(LogContent log, Action1<Integer> action) {
        mResponse.updateLog(log, action);
    }

    void removeDraft(String id) {
        mResponse.removeDraft(id, null);
    }

}
