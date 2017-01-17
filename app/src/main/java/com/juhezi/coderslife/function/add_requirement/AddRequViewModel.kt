package com.juhezi.coderslife.function.add_requirement

import android.util.Log
import com.juhezi.coderslife.BaseViewModel
import com.juhezi.coderslife.databinding.ActAddRequirementBinding
import com.juhezi.coderslife.entry.LogContent
import com.juhezi.coderslife.entry.Title

/**
 * Created by qiao1 on 2017/1/16.
 */
class AddRequViewModel(binding: ActAddRequirementBinding) : BaseViewModel<ActAddRequirementBinding>(binding) {

    private var TAG = "AddRequViewModel"

    private var content: LogContent? = null

    /**
     * 初始化数据
     */
    override fun initData() {
        binding.title = Title(Title.ADD_REQUIREMENT)
        content = LogContent("", LogContent.TYPE_REQUIREMENT)
        binding.logContent = content
    }

    /**
     * 提交数据
     */
    fun submitLogContent(action: (() -> Unit)?) {
        Log.i(TAG, "${content?.time}————————${content?.content}")
    }

}