package com.knasirayaz.nanohealthsuitedemo.data.source.remote

import com.knasirayaz.nanohealthsuitedemo.domain.model.LoginResponse
import com.knasirayaz.nanohealthsuitedemo.domain.model.ProductsResponse
import com.knasirayaz.nanohealthsuitedemo.domain.model.ProductsResponseItem
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface Webservice {
    @POST("auth/login")
    suspend fun login(@Body hashMap: HashMap<String, String>) : LoginResponse

    @GET("products")
    suspend fun getAllProducts() : ProductsResponse

    @GET("products/{id}")
    suspend fun getSingleProduct(@Path("id") id : Int) : ProductsResponseItem



}
