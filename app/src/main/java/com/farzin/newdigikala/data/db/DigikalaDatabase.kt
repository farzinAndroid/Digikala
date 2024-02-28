package com.farzin.newdigikala.data.db

import androidx.room.Database
import androidx.room.PrimaryKey
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.farzin.newdigikala.data.model.basket.CartItem
import com.farzin.newdigikala.data.model.profile.FavItem
import com.farzin.newdigikala.util.Constants
import com.farzin.newdigikala.util.Constants.FAV_LIST_TABLE

@Database(entities = [CartItem::class, FavItem::class], version = 2, exportSchema = false)
abstract class DigikalaDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
    abstract fun favDao(): FavDao


    companion object {

        val MIGRATION_1_TO_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL(
                    "CREATE TABLE IF NOT EXISTS `${FAV_LIST_TABLE}` " +
                            "(`id` TEXT NOT NULL, " +
                            "`name` TEXT NOT NULL, " +
                            "`seller` TEXT NOT NULL, " +
                            "`price` INTEGER NOT NULL, " +
                            "`discountPercent` INTEGER NOT NULL, " +
                            "`image` TEXT NOT NULL, " +
                            "`star` REAL NOT NULL, " +
                            "PRIMARY KEY(`id`))"
                )
            }

        }
    }

}