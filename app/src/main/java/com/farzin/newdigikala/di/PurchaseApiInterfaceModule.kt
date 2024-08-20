package com.farzin.newdigikala.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.farzin.newdigikala.data.remote.ProfileApiInterface
import com.farzin.newdigikala.data.remote.PurchaseApiInterface
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PurchaseApiInterfaceModule {

    @Singleton
    @Provides
    fun provideProfileApiService(@Named("zarinpal") retrofit: Retrofit): PurchaseApiInterface =
        retrofit.create(PurchaseApiInterface::class.java)

}