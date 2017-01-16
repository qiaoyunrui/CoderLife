package com.juhezi.coderslife.add_requirement

import android.util.Log
import com.juhezi.coderslife.BaseViewModel
import com.juhezi.coderslife.databinding.ActAddRequirementBinding
import com.juhezi.coderslife.entry.Title

/**
 * Created by qiao1 on 2017/1/16.
 */
class AddRequViewModel(binding: ActAddRequirementBinding) : BaseViewModel<ActAddRequirementBinding>(binding) {

    private var TAG = "AddRequViewModel"

    /**
     * 初始化数据
     */
    override fun initData() {
        binding.title = Title(Title.ADD_REQUIREMENT)
    }

}