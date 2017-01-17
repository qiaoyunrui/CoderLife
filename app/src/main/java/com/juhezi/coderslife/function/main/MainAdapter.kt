package com.juhezi.coderslife.function.main

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import com.juhezi.coderslife.R
import com.juhezi.coderslife.databinding.ItemRequirementBinding
import com.juhezi.coderslife.entry.LogContent
import java.util.*

/**
 * Created by qiao1 on 2017/1/11.
 */
class MainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //数据
    var logContents: ArrayList<LogContent> = ArrayList<LogContent>()
        set(value) {
            field.clear()
            field = value
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        when (getItemViewType(position)) {
            LogContent.TYPE_REQUIREMENT -> {
                var requirementItemHolder = holder as RequirementItemHolder
                requirementItemHolder.binding.logContent = logContents[position]
                requirementItemHolder.binding.executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            LogContent.TYPE_REQUIREMENT -> {
                var binding: ItemRequirementBinding = DataBindingUtil.inflate<ItemRequirementBinding>(
                        LayoutInflater.from(parent?.context),
                        R.layout.item_requirement,
                        parent,
                        false)
                RequirementItemHolder(binding.root, binding)
            }
            else -> null!!
        }
    }

    override fun getItemCount(): Int = logContents.size

    /**
     * 获取ItemType
     */
    override fun getItemViewType(position: Int): Int = logContents[position].contentType

    /**
     * 需求Item
     */
    class RequirementItemHolder(itemView: View, var binding: ItemRequirementBinding)
        : RecyclerView.ViewHolder(itemView) {

        var checkBox: CheckBox? = null
        var editButton: ImageView? = null

        init {
            checkBox = itemView.findViewById(R.id.cb_select_item_requirement) as CheckBox
            editButton = itemView.findViewById(R.id.img_edit_item_requirement) as ImageView
        }

    }

}