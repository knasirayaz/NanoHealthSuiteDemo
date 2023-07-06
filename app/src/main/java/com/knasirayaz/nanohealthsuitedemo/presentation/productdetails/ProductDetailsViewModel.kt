package com.knasirayaz.nanohealthsuitedemo.presentation.productdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.knasirayaz.nanohealthsuitedemo.domain.common.ResultStates
import com.knasirayaz.nanohealthsuitedemo.domain.model.ProductsResponse
import com.knasirayaz.nanohealthsuitedemo.domain.model.ProductsResponseItem
import com.knasirayaz.nanohealthsuitedemo.domain.usecase.GetAllProducts
import com.knasirayaz.nanohealthsuitedemo.domain.usecase.GetProductDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private var getProductDetails: GetProductDetails
) : ViewModel() {

    private val productDetailsObserver = MutableLiveData<ResultStates<ProductsResponseItem>>()

    fun getProductDetailsObserver(): LiveData<ResultStates<ProductsResponseItem>> {
        return productDetailsObserver
    }

    fun getProductDetails(id : Int) {
        viewModelScope.launch {
            productDetailsObserver.value = ResultStates.Loading(true)
            productDetailsObserver.postValue(getProductDetails.invoke(id))
            productDetailsObserver.value = ResultStates.Loading(false)
        }
    }

}