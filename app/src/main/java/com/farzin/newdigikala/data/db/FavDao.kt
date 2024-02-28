package com.farzin.newdigikala.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.farzin.newdigikala.data.model.profile.FavItem
import kotlinx.coroutines.flow.Flow

@Dao
interface FavDao {

    @Query("select * from fav_list")
    fun getAllFavorites() : Flow<List<FavItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favItem: FavItem)

    @Delete
    suspend fun deleteFavorite(favItem: FavItem)

    @Query("delete from fav_list")
    suspend fun deleteAllFavorites()

    @Query("select exists( select * from fav_list where id = :id)")
    fun isFavItemExist(id:String) : Flow<Boolean>

}