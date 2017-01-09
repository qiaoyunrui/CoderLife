package com.juhezi.coderslife.main

import android.support.v4.app.Fragment
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar

import com.juhezi.coderslife.R
import com.juhezi.coderslife.SingleFragmentActivity

class MainActivity : SingleFragmentActivity() {

    private var mToolbar: Toolbar? = null
    private var mActionBar: ActionBar? = null

    override fun getFragment(): Fragment = MainFragment.getInstance()

    override fun getActLayoutRes(): Int = R.layout.act_main

    override fun getFragContainerId(): Int = R.id.rl_act_main_frag

    override fun init() {
        initActionBar()
    }

    private fun initActionBar() {
        mToolbar = findViewById(R.id.tb_act_main) as Toolbar?
        setSupportActionBar(mToolbar)
        mActionBar = supportActionBar
    }

}
