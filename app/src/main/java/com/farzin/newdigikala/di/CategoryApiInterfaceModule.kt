package com.farzin.newdigikala.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.farzin.newdigikala.data.remote.CategoryApiInterface
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CategoryApiInterfaceModule {

    @Singleton
    @Provides
    fun provideCategoryApiService(retrofit: Retrofit): CategoryApiInterface =
        retrofit.create(CategoryApiInterface::class.java)

}