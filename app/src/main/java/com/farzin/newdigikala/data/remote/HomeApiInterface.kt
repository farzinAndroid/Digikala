package com.farzin.newdigikala.data.remote

import com.farzin.newdigikala.data.model.ResponseResult
import com.farzin.newdigikala.data.model.home.AmazingItem
import com.farzin.newdigikala.data.model.home.MainCategory
import com.farzin.newdigikala.data.model.home.Slider
import com.farzin.newdigikala.data.model.home.StoreProduct
import retrofit2.Response
import retrofit2.http.GET

interface HomeApiInterface {

    @GET("getSlider")
    suspend fun getSlider(): Response<ResponseResult<List<Slider>>>

    @GET("getAmazingProducts")
    suspend fun getAmazingItems(): Response<ResponseResult<List<AmazingItem>>>

    @GET("getSuperMarketAmazingProducts")
    suspend fun getAmazingSuperMarketItems(): Response<ResponseResult<List<AmazingItem>>>

    @GET("get4Banners")
    suspend fun getProposalBanners(): Response<ResponseResult<List<Slider>>>

    @GET("getCategories")
    suspend fun getCategories(): Response<ResponseResult<List<MainCategory>>>

    @GET("getCenterBanners")
    suspend fun getCenterBanners(): Response<ResponseResult<List<Slider>>>

    @GET("getBestsellerProducts")
    suspend fun getBestSellerItems(): Response<ResponseResult<List<StoreProduct>>>

    @GET("getMostVisitedProducts")
    suspend fun getMostVisitedItems(): Response<ResponseResult<List<StoreProduct>>>

    @GET("getMostFavoriteProducts")
    suspend fun getMostFavoriteItems(): Response<ResponseResult<List<StoreProduct>>>

    @GET("getMostDiscountedProducts")
    suspend fun getMostDiscountedItems(): Response<ResponseResult<List<StoreProduct>>>


}