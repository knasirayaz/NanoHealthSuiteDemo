package com.knasirayaz.nanohealthsuitedemo.presentation.allproducts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.knasirayaz.nanohealthsuitedemo.domain.common.ResultStates
import com.knasirayaz.nanohealthsuitedemo.domain.model.LoginResponse
import com.knasirayaz.nanohealthsuitedemo.domain.model.ProductsResponse
import com.knasirayaz.nanohealthsuitedemo.domain.usecase.GetAllProducts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllProductsViewModel @Inject constructor(
    private var getAllProductsUseCase: GetAllProducts) : ViewModel() {

    private val productsObserver = MutableLiveData<ResultStates<ProductsResponse>>()

    fun getProductsObserver(): LiveData<ResultStates<ProductsResponse>> {
        return productsObserver
    }

    fun getAllProducts() {
        viewModelScope.launch {
            productsObserver.value = ResultStates.Loading(true)
            productsObserver.postValue(getAllProductsUseCase.invoke())
            productsObserver.value = ResultStates.Loading(false)
        }
    }

}