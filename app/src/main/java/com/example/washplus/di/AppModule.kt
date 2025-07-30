package com.example.washplus.di

import com.example.washplus.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Named("databaseName")
    fun provideDataBaseName(): String = BuildConfig.NEWS_DATABASE_NAME


}