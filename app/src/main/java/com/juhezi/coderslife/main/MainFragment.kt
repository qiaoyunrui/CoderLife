package com.juhezi.coderslife.main

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.juhezi.coderslife.R
import com.juhezi.coderslife.add_requirement.AddRequirementActivity
import com.juhezi.coderslife.databinding.FragMainBinding
import com.konifar.fab_transformation.FabTransformation

/**
 * Created by qiao1 on 2017/1/9.
 */
class MainFragment : Fragment() {
    companion object {
        fun getInstance() = Holder.sInstance
    }

    private object Holder {
        val sInstance = MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate<FragMainBinding>(inflater, R.layout.frag_main, container, false)
        initEvent(binding?.root)
        initRecyclerView()
        return binding?.root
    }

    private var binding: FragMainBinding? = null
    private var mFabAdd: FloatingActionButton? = null
    private var mCvNavWrapper: CardView? = null
    private var mNavAdd: NavigationView? = null
    private var mVOverlay: View? = null
    private var mRvList: RecyclerView? = null
    var isItemOpen = false
    private var mAdapter: MainAdapter = MainAdapter()

    private fun initEvent(rootView: View?) {
        mFabAdd = rootView?.findViewById(R.id.fab_add) as FloatingActionButton
        mNavAdd = rootView?.findViewById(R.id.nav_frag_main_add_item) as NavigationView?
        mCvNavWrapper = rootView?.findViewById(R.id.cv_frag_main_add_item_wrapper) as CardView?
        mNavAdd = rootView?.findViewById(R.id.nav_frag_main_add_item) as NavigationView?
        mVOverlay = rootView?.findViewById(R.id.v_frag_main_overlay)
        mRvList = rootView?.findViewById(R.id.rv_list_frag_main) as RecyclerView?
        mFabAdd?.setOnClickListener { openItem() }
        mVOverlay?.setOnClickListener { closeItem() }
        mNavAdd?.setNavigationItemSelectedListener {
            closeItem()
            when (it.itemId) {
                R.id.item_nav_add_main_requirement -> openAddRequirementAct()
            }
            true
        }
    }

    fun initRecyclerView() {
        mRvList?.layoutManager = LinearLayoutManager(context)
        mRvList?.adapter = mAdapter
    }

    private fun openItem() {
        FabTransformation.with(mFabAdd)
                .setOverlay(mVOverlay)
                .transformTo(mCvNavWrapper)
        isItemOpen = true
    }

    fun closeItem() {
        FabTransformation.with(mFabAdd)
                .setOverlay(mVOverlay)
                .transformFrom(mCvNavWrapper)
        isItemOpen = false
    }

    /**
     * 打开添加需求界面
     */
    fun openAddRequirementAct() {
        var addRequIntent = Intent(context, AddRequirementActivity::class.java)
        startActivity(addRequIntent)
    }

}