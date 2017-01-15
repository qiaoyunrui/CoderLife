package com.juhezi.coderslife.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.juhezi.coderslife.R

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
        var rootView = inflater?.inflate(R.layout.frag_main, container, false)
        initEvent(rootView)
        return rootView
    }

    private var mFabAdd: FloatingActionButton? = null
    private var mCvNavWrapper: CardView? = null
    private var mNavAdd: NavigationView? = null
    private var mVOverlay: View? = null

    private fun initEvent(rootView: View?) {
        mFabAdd = rootView?.findViewById(R.id.fab_add) as FloatingActionButton
        mNavAdd = rootView?.findViewById(R.id.nav_frag_main_add_item) as NavigationView?
        mCvNavWrapper = rootView?.findViewById(R.id.nav_frag_main_add_item) as CardView?
        mFabAdd?.setOnClickListener {

        }
    }

}