package com.knasirayaz.nanohealthsuitedemo.presentation.allproducts

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.knasirayaz.nanohealthsuitedemo.R
import com.knasirayaz.nanohealthsuitedemo.databinding.FragmentAllProductsBinding
import com.knasirayaz.nanohealthsuitedemo.domain.common.ResultStates
import com.knasirayaz.nanohealthsuitedemo.domain.common.SnackBarTypes
import com.knasirayaz.nanohealthsuitedemo.domain.common.launchActivity
import com.knasirayaz.nanohealthsuitedemo.domain.common.showToast
import com.knasirayaz.nanohealthsuitedemo.domain.model.ProductsResponse
import com.knasirayaz.nanohealthsuitedemo.domain.model.ProductsResponseItem
import com.knasirayaz.nanohealthsuitedemo.presentation.base.BaseFragment
import com.knasirayaz.nanohealthsuitedemo.presentation.base.HomeActivity
import com.knasirayaz.nanohealthsuitedemo.presentation.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllProductsScreen : BaseFragment<FragmentAllProductsBinding>(R.layout.fragment_all_products) {

    private val viewModel: AllProductsViewModel by viewModels()
    private var mProductsAdapter: ProductsAdapter? = null
    private var mProducts: ArrayList<ProductsResponseItem> = ArrayList()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        apiCall()
        setAdapter()
    }

    private fun apiCall() {
        if (mProducts.isEmpty())
            viewModel.getProductsObserver().observe(viewLifecycleOwner) { resultStates ->
                when (resultStates) {
                    is ResultStates.Failed -> {
                        showToast(requireView(), resultStates.error, SnackBarTypes.ERROR)
                    }

                    is ResultStates.ValidationFailed -> {
                        showToast(requireView(), getString(resultStates.error), SnackBarTypes.ERROR)
                    }

                    is ResultStates.Success -> {
                        mProducts.clear()
                        mProducts.addAll(resultStates.data)
                        mProductsAdapter?.notifyDataSetChanged()
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
            }.also {
                viewModel.getAllProducts()
            }
    }

    private fun setAdapter() {
        mProductsAdapter = ProductsAdapter(mProducts) {
            findNavController().navigate(
                R.id.action_navigation_home_to_productsDetailScreen,
                bundleOf("id" to it)
            )
        }
        viewBinding.rvProducts.adapter = mProductsAdapter
    }
}