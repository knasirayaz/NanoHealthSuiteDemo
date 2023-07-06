package com.knasirayaz.nanohealthsuitedemo.data.repository

import com.knasirayaz.nanohealthsuitedemo.data.source.remote.Webservice
import com.knasirayaz.nanohealthsuitedemo.domain.common.ResultStates
import com.knasirayaz.nanohealthsuitedemo.domain.model.ProductsResponse
import com.knasirayaz.nanohealthsuitedemo.domain.model.ProductsResponseItem
import com.knasirayaz.nanohealthsuitedemo.domain.repository.ProductsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val webService: Webservice
) : ProductsRepository {
    override suspend fun getAllProducts(): ResultStates<ProductsResponse> =
        withContext(Dispatchers.IO) {
            try {
                val results = webService.getAllProducts()
                if (!results.isEmpty()) {
                    return@withContext ResultStates.Success(results)
                } else
                    return@withContext ResultStates.Failed("Something went wrong")
            } catch (e: UnknownHostException) {
                return@withContext ResultStates.Failed(e.message.toString())
            } catch (e: HttpException) {
                return@withContext ResultStates.Failed(
                    e.response()?.errorBody()?.string() ?: e.message.toString()
                )
            }
        }

    override suspend fun getSingleProduct(id: Int): ResultStates<ProductsResponseItem> =
        withContext(Dispatchers.IO) {
            try {
                val results = webService.getSingleProduct(id)
                return@withContext ResultStates.Success(results)
            } catch (e: UnknownHostException) {
                return@withContext ResultStates.Failed(e.message.toString())
            } catch (e: HttpException) {
                return@withContext ResultStates.Failed(
                    e.response()?.errorBody()?.string() ?: e.message.toString()
                )
            }
        }
}