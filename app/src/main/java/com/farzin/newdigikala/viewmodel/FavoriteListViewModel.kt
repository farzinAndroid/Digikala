package com.farzin.newdigikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farzin.newdigikala.data.model.profile.FavItem
import com.farzin.newdigikala.repository.FavoriteListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteListViewModel @Inject constructor(
    private val favoriteListRepository: FavoriteListRepository,
) : ViewModel() {

    val allFavoriteItems = favoriteListRepository.getAllFavorites


    fun addFavorite(favItem: FavItem) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteListRepository.addFavorite(favItem)
        }
    }

    fun deleteFavorite(favItem: FavItem) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteListRepository.deleteFavorite(favItem)
        }
    }


    fun isFavItemExist(id: String): Flow<Boolean> =
        favoriteListRepository.isFavItemExist(id)


}