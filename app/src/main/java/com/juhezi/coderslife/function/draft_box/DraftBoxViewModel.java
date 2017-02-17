package com.juhezi.coderslife.function.draft_box;

import android.content.Context;

import com.juhezi.coderslife.BaseViewModel;
import com.juhezi.coderslife.databinding.FragDraftBoxBinding;
import com.juhezi.coderslife.entry.Title;

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
}
