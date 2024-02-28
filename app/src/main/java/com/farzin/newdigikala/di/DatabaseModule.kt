package com.farzin.newdigikala.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.farzin.newdigikala.data.db.DigikalaDatabase
import com.farzin.newdigikala.data.db.DigikalaDatabase.Companion.MIGRATION_1_TO_2
import com.farzin.newdigikala.util.Constants.DATABASE_NAME
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ) = Room.databaseBuilder(
        context,
        DigikalaDatabase::class.java,
        DATABASE_NAME
    )
        .addMigrations(MIGRATION_1_TO_2)
        .build()

}