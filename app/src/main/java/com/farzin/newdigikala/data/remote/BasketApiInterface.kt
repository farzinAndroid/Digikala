package com.farzin.newdigikala.data.remote

import com.farzin.newdigikala.data.model.ResponseResult
import com.farzin.newdigikala.data.model.home.StoreProduct
import retrofit2.Response
import retrofit2.http.GET


interface BasketApiInterface {

    @GET("v1/getAllProducts")
    suspend fun getSuggestedItems(): Response<ResponseResult<List<StoreProduct>>>

}