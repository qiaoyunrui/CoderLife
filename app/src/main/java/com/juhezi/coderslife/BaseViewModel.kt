package com.juhezi.coderslife

import android.databinding.ViewDataBinding
import android.util.Log

/**
 * Created by qiao1 on 2017/1/9.
 */
abstract class BaseViewModel<T : ViewDataBinding>(open var binding: T) {

    init {
        initData()
    }

    abstract fun initData()
}