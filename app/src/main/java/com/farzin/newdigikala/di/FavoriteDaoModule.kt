package com.farzin.newdigikala.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.farzin.newdigikala.data.db.CartDao
import com.farzin.newdigikala.data.db.DigikalaDatabase
import com.farzin.newdigikala.data.db.FavDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoriteDaoModule {

    @Provides
    @Singleton
    fun provideFavoriteDao(
        database: DigikalaDatabase,
    ): FavDao = database.favDao()
}