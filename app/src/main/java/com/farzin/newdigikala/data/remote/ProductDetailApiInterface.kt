package com.farzin.newdigikala.data.remote

import com.farzin.newdigikala.data.model.ResponseResult
import com.farzin.newdigikala.data.model.home.StoreProduct
import com.farzin.newdigikala.data.model.product_detail.Comment
import com.farzin.newdigikala.data.model.product_detail.NewComment
import com.farzin.newdigikala.data.model.product_detail.ProductDetail
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ProductDetailApiInterface {

    @GET("getProductById")
    suspend fun getProductById(
        @Query("id") id: String,
    ): Response<ResponseResult<ProductDetail>>


    @GET("getSimilarProducts")
    suspend fun getSimilarProducts(
        @Query("categoryId") categoryId: String,
    ): Response<ResponseResult<List<StoreProduct>>>

}