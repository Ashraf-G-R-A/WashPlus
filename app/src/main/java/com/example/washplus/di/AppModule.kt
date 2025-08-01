package com.example.washplus.di

import android.content.Context
import androidx.room.Room
import com.example.washplus.BuildConfig
import com.example.washplus.network.local.WashPlusDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Named("databaseName")
    fun provideDataBaseName(): String = BuildConfig.NEWS_DATABASE_NAME

    @Provides
    @Singleton
    fun provideNewsDataBase(
        @ApplicationContext context: Context,
        @Named("databaseName") databaseName: String
    ): WashPlusDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = WashPlusDatabase::class.java,
            name = databaseName
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(newsDataBase: WashPlusDatabase) = newsDataBase.newsDao

}