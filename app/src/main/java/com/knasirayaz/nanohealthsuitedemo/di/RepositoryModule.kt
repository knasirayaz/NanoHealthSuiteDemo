package com.knasirayaz.nanohealthsuitedemo.di

import com.knasirayaz.nanohealthsuitedemo.data.repository.LoginRepositoryImpl
import com.knasirayaz.nanohealthsuitedemo.data.repository.ProductsRepositoryImpl
import com.knasirayaz.nanohealthsuitedemo.data.source.remote.Webservice
import com.knasirayaz.nanohealthsuitedemo.domain.common.SessionManager
import com.knasirayaz.nanohealthsuitedemo.domain.repository.LoginRepository
import com.knasirayaz.nanohealthsuitedemo.domain.repository.ProductsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideLoginRepository(webService: Webservice, sessionManager: SessionManager): LoginRepository {
        return LoginRepositoryImpl(webService, sessionManager = sessionManager)
    }

    @Singleton
    @Provides
    fun provideProductsRepository(webService: Webservice): ProductsRepository {
        return ProductsRepositoryImpl(webService)
    }

}