package com.farzin.newdigikala.data.remote

import com.farzin.newdigikala.data.model.ResponseResult
import com.farzin.newdigikala.data.model.basket.CartDetails
import com.farzin.newdigikala.data.model.checkout.OrderDetails
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CheckoutApiInterface {

    @GET("v1/getShippingCost")
    suspend fun getShippingCost(
        @Query("address") address: String,
    ): Response<ResponseResult<Int>>

    @POST("v1/setNewOrder")
    suspend fun setNewOrder(
        @Body orderDetails: OrderDetails,
    ): Response<ResponseResult<String>>


}