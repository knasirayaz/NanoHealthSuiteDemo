package com.knasirayaz.nanohealthsuitedemo.domain.usecase

import com.knasirayaz.nanohealthsuitedemo.domain.common.ResultStates
import com.knasirayaz.nanohealthsuitedemo.domain.model.ProductsResponse
import com.knasirayaz.nanohealthsuitedemo.domain.model.ProductsResponseItem
import com.knasirayaz.nanohealthsuitedemo.domain.repository.ProductsRepository
import javax.inject.Inject

class GetProductDetails @Inject constructor(private var repository: ProductsRepository){
    suspend operator fun invoke(id : Int): ResultStates<ProductsResponseItem> {
        return repository.getSingleProduct(id)
    }
}