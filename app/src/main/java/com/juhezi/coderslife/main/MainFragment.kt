package com.juhezi.coderslife.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.juhezi.coderslife.R
import com.juhezi.coderslife.test.TestAct

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

    private var fabAdd: FloatingActionButton? = null

    private fun initEvent(rootView: View?) {
        fabAdd = rootView?.findViewById(R.id.fab_add) as FloatingActionButton
        fabAdd?.setOnClickListener {
//            startActivity(Intent(context, TestAct::class.java))
        }
    }

}