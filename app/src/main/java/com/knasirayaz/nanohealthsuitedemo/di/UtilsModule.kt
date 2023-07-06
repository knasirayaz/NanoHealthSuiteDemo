package com.knasirayaz.nanohealthsuitedemo.di

import android.app.Application
import com.google.gson.Gson
import com.knasirayaz.nanohealthsuitedemo.domain.common.AppPreferences
import com.knasirayaz.nanohealthsuitedemo.domain.common.CredentialValidator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun provideFieldValidation(): CredentialValidator {
        return CredentialValidator
    }

    @Provides
    @Singleton
    fun provideAppPreferences(application: Application, gson : Gson): AppPreferences {
        return AppPreferences(application, gson)
    }



}