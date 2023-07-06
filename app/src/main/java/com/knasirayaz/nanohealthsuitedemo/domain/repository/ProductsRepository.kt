package com.knasirayaz.nanohealthsuitedemo.domain.repository

import com.knasirayaz.nanohealthsuitedemo.domain.common.ResultStates
import com.knasirayaz.nanohealthsuitedemo.domain.model.ProductsResponse
import com.knasirayaz.nanohealthsuitedemo.domain.model.ProductsResponseItem

interface ProductsRepository {
    suspend fun getAllProducts() : ResultStates<ProductsResponse>

    suspend fun getSingleProduct(id : Int) : ResultStates<ProductsResponseItem>

}