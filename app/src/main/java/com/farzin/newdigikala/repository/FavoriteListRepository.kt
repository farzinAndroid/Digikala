package com.farzin.newdigikala.repository

import com.farzin.newdigikala.data.db.FavDao
import com.farzin.newdigikala.data.model.profile.FavItem
import javax.inject.Inject

class FavoriteListRepository @Inject constructor(
    private val favDao: FavDao
) {

    val getAllFavorites = favDao.getAllFavorites()


    suspend fun addFavorite(favItem: FavItem) = favDao.addFavorite(favItem)
    suspend fun deleteFavorite(favItem: FavItem) = favDao.deleteFavorite(favItem)
    suspend fun deleteAllFavorites() = favDao.deleteAllFavorites()
    fun isFavItemExist(id:String) = favDao.isFavItemExist(id)


}