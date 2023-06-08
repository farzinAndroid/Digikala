package com.farzin.newdigikala.repository

import com.farzin.newdigikala.data.model.home.AmazingItem
import com.farzin.newdigikala.data.model.home.MainCategory
import com.farzin.newdigikala.data.model.home.Slider
import com.farzin.newdigikala.data.model.home.StoreProduct
import com.farzin.newdigikala.data.remote.BaseApiResponse
import com.farzin.newdigikala.data.remote.HomeApiInterface
import com.farzin.newdigikala.data.remote.NetworkResult
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api: HomeApiInterface) : BaseApiResponse() {

    suspend fun getSlider(): NetworkResult<List<Slider>> =
        safeApiCall {
            api.getSlider()
        }

    suspend fun getAmazingItems(): NetworkResult<List<AmazingItem>> =
        safeApiCall {
            api.getAmazingItems()
        }

    suspend fun getAmazingSuperMarketItems(): NetworkResult<List<AmazingItem>> =
        safeApiCall {
            api.getAmazingSuperMarketItems()
        }

    suspend fun getProposalBanners(): NetworkResult<List<Slider>> =
        safeApiCall {
            api.getProposalBanners()
        }

    suspend fun getCategories(): NetworkResult<List<MainCategory>> =
        safeApiCall {
            api.getCategories()
        }

    suspend fun getCenterBanners(): NetworkResult<List<Slider>> =
        safeApiCall {
            api.getCenterBanners()
        }


    suspend fun getBestSellerItems(): NetworkResult<List<StoreProduct>> =
        safeApiCall {
            api.getBestSellerItems()
        }


    suspend fun getMostVisitedItems(): NetworkResult<List<StoreProduct>> =
        safeApiCall {
            api.getMostVisitedItems()
        }

    suspend fun getMostFavoriteItems(): NetworkResult<List<StoreProduct>> =
        safeApiCall {
            api.getMostFavoriteItems()
        }

    suspend fun getMostDiscountedItems(): NetworkResult<List<StoreProduct>> =
        safeApiCall {
            api.getMostDiscountedItems()
        }


}