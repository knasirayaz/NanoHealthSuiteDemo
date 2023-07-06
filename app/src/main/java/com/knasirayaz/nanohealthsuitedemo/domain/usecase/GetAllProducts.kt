package com.knasirayaz.nanohealthsuitedemo.domain.usecase

import com.knasirayaz.nanohealthsuitedemo.domain.common.ResultStates
import com.knasirayaz.nanohealthsuitedemo.domain.model.LoginResponse
import com.knasirayaz.nanohealthsuitedemo.domain.model.ProductsResponse
import com.knasirayaz.nanohealthsuitedemo.domain.repository.LoginRepository
import com.knasirayaz.nanohealthsuitedemo.domain.repository.ProductsRepository
import javax.inject.Inject

class GetAllProducts @Inject constructor(private var repository: ProductsRepository){
    suspend operator fun invoke(): ResultStates<ProductsResponse> {
        return repository.getAllProducts()
    }
}