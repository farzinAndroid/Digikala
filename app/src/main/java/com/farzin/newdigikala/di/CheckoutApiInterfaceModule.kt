package com.farzin.newdigikala.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.farzin.newdigikala.data.remote.CheckoutApiInterface
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CheckoutApiInterfaceModule {

    @Singleton
    @Provides
    fun provideCheckoutApiService(retrofit: Retrofit) : CheckoutApiInterface =
        retrofit.create(CheckoutApiInterface::class.java)

}