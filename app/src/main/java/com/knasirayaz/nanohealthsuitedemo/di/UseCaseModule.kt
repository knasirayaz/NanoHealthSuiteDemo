package com.knasirayaz.nanohealthsuitedemo.di

import com.knasirayaz.nanohealthsuitedemo.data.repository.LoginRepositoryImpl
import com.knasirayaz.nanohealthsuitedemo.data.repository.ProductsRepositoryImpl
import com.knasirayaz.nanohealthsuitedemo.domain.usecase.GetAllProducts
import com.knasirayaz.nanohealthsuitedemo.domain.usecase.Login
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideLoginUseCase(loginRepository: LoginRepositoryImpl): Login {
        return Login(loginRepository)
    }

    @Singleton
    @Provides
    fun provideProductsUseCase(productsRepositoryImpl: ProductsRepositoryImpl): GetAllProducts {
        return GetAllProducts(productsRepositoryImpl)
    }
}