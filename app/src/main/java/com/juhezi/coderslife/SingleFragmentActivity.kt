package com.juhezi.coderslife

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.util.Log

/**
 * Created by qiao1 on 2017/1/9.
 */
abstract class SingleFragmentActivity : AppCompatActivity() {

    private var TAG = "SingleFragmentActivity"

    protected open fun init() {
    }

    protected abstract fun getFragment(): Fragment

    @LayoutRes
    protected abstract fun getActLayoutRes(): Int

    @IdRes
    protected abstract fun getFragContainerId(): Int

    protected var mFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG,this.toString())
        setContentView(getActLayoutRes())
        var fragmentManager = supportFragmentManager
        mFragment = fragmentManager.findFragmentById(getFragContainerId())
        if (mFragment == null) {
            mFragment = getFragment()
            fragmentManager.beginTransaction()
                    .add(getFragContainerId(), mFragment)
                    .commit()
        }
        init()
    }

}