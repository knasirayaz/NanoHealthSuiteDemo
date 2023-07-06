package com.knasirayaz.nanohealthsuitedemo.presentation.base

import android.content.Context
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity

abstract class BaseActivity<DataBinding : ViewDataBinding> : FragmentActivity() {
    private lateinit var dataBinding: DataBinding
    protected val viewBinding get() = dataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(
            this,
            getLayoutId()
        ) //DataBindingUtil.inflate(layoutInflater, getLayoutId(), null, false)
    }

    abstract fun getLayoutId(): Int

}