package com.farzin.newdigikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.farzin.newdigikala.data.model.home.AmazingItem
import com.farzin.newdigikala.data.model.home.MainCategory
import com.farzin.newdigikala.data.model.home.Slider
import com.farzin.newdigikala.data.model.home.StoreProduct
import com.farzin.newdigikala.data.remote.NetworkResult
import com.farzin.newdigikala.repository.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {

    val slider = MutableStateFlow<NetworkResult<List<Slider>>>(NetworkResult.Loading())
    val amazingItems = MutableStateFlow<NetworkResult<List<AmazingItem>>>(NetworkResult.Loading())
    val superMarketItems =
        MutableStateFlow<NetworkResult<List<AmazingItem>>>(NetworkResult.Loading())
    val banners = MutableStateFlow<NetworkResult<List<Slider>>>(NetworkResult.Loading())
    val categories = MutableStateFlow<NetworkResult<List<MainCategory>>>(NetworkResult.Loading())
    val centerBannerItems = MutableStateFlow<NetworkResult<List<Slider>>>(NetworkResult.Loading())
    val bestSellerItems =
        MutableStateFlow<NetworkResult<List<StoreProduct>>>(NetworkResult.Loading())
    val mostVisitedItems =
        MutableStateFlow<NetworkResult<List<StoreProduct>>>(NetworkResult.Loading())
    val mostFavoriteItems =
        MutableStateFlow<NetworkResult<List<StoreProduct>>>(NetworkResult.Loading())
    val mostDiscountedItems =
        MutableStateFlow<NetworkResult<List<StoreProduct>>>(NetworkResult.Loading())


    suspend fun getAllDataFromServer() {
        viewModelScope.launch {

            //fire and forget
            launch {
                slider.emit(repository.getSlider())
            }

            launch {
                amazingItems.emit(repository.getAmazingItems())
            }

            launch {
                superMarketItems.emit(repository.getAmazingSuperMarketItems())
            }

            launch {
                banners.emit(repository.getProposalBanners())
            }

            launch {
                categories.emit(repository.getCategories())
            }

            launch {
                centerBannerItems.emit(repository.getCenterBanners())
            }

            launch {
                bestSellerItems.emit(repository.getBestSellerItems())
            }

            launch {
                mostVisitedItems.emit(repository.getMostVisitedItems())
            }

            launch {
                mostFavoriteItems.emit(repository.getMostFavoriteItems())
            }

            launch {
                mostDiscountedItems.emit(repository.getMostDiscountedItems())
            }

        }
    }

}