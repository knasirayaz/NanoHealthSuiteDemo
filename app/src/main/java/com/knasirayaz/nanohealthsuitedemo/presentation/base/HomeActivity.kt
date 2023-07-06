package com.knasirayaz.nanohealthsuitedemo.presentation.base

import android.os.Bundle
import com.knasirayaz.nanohealthsuitedemo.R
import com.knasirayaz.nanohealthsuitedemo.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }
}