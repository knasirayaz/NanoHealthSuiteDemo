package com.knasirayaz.nanohealthsuitedemo.presentation.login

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.knasirayaz.nanohealthsuitedemo.R
import com.knasirayaz.nanohealthsuitedemo.databinding.ActivityLoginBinding
import com.knasirayaz.nanohealthsuitedemo.domain.common.ResultStates
import com.knasirayaz.nanohealthsuitedemo.domain.common.SnackBarTypes
import com.knasirayaz.nanohealthsuitedemo.domain.common.showToast
import com.knasirayaz.nanohealthsuitedemo.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.knasirayaz.nanohealthsuitedemo.BuildConfig
import com.knasirayaz.nanohealthsuitedemo.domain.common.Constants
import com.knasirayaz.nanohealthsuitedemo.domain.common.launchActivity
import com.knasirayaz.nanohealthsuitedemo.presentation.base.HomeActivity

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    private val viewModel: LoginViewModel by viewModels()


    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(Constants.IS_TEST){
            viewBinding.edtEmailAddress.setText("mor_2314")
           viewBinding.edtPassword.setText("83r5^_")
        }

        viewBinding.btnLogin.setOnClickListener {
            viewBinding.btnLogin.isEnabled = false

            viewModel.doLogin(
                hashMapOf(
                    "username" to viewBinding.edtEmailAddress.text.toString(),
                    "password" to viewBinding.edtPassword.text.toString())
            )
        }

        viewModel.getLoginObserver().observe(this, Observer { resultStates ->
            if(resultStates !is ResultStates.Loading){
                viewBinding.btnLogin.isEnabled = true
            }

            when(resultStates){
                is ResultStates.Failed -> {
                    showToast(viewBinding.rootView, resultStates.error, SnackBarTypes.ERROR)
                }
                is ResultStates.ValidationFailed ->{
                    showToast(viewBinding.rootView, getString(resultStates.error), SnackBarTypes.ERROR)
                }
                is ResultStates.Success ->{
                    launchActivity<HomeActivity>()
                    finish()
                }
                is ResultStates.Loading -> {
                    if(resultStates.isLoading){
                        viewBinding.progressBar.visibility = View.VISIBLE
                    }else{
                        viewBinding.progressBar.visibility = View.GONE
                    }
                }
                else -> {}
            }
        })
    }

}