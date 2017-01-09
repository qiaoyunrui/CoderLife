package com.juhezi.coderslife.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.juhezi.coderslife.R

/**
 * Created by qiao1 on 2017/1/9.
 */
class MainFragment private constructor() : Fragment() {
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

    private fun  initEvent(rootView: View?) {
    }

}