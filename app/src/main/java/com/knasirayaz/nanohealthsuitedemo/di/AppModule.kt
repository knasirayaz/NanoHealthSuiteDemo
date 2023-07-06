package com.knasirayaz.nanohealthsuitedemo.di

import com.google.gson.Gson
import com.knasirayaz.nanohealthsuitedemo.data.repository.LoginRepositoryImpl
import com.knasirayaz.nanohealthsuitedemo.data.source.remote.Webservice
import com.knasirayaz.nanohealthsuitedemo.domain.common.AppPreferences
import com.knasirayaz.nanohealthsuitedemo.domain.common.SessionManager
import com.knasirayaz.nanohealthsuitedemo.domain.repository.LoginRepository
import com.knasirayaz.nanohealthsuitedemo.domain.usecase.Login
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [UseCaseModule::class, RepositoryModule::class, UtilsModule::class])
@InstallIn(SingletonComponent::class)
object AppModule{

    @Singleton
    @Provides
    fun provideSessionManager(appPreferences: AppPreferences): SessionManager {
        return SessionManager(appPreferences)
    }
}