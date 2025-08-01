package com.example.washplus.di

import com.example.washplus.wash.data.repo.WashPlusImpl
import com.example.washplus.wash.domain.repo.IWashPlus
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWashPlusRepository(
        impl: WashPlusImpl
    ): IWashPlus
}
