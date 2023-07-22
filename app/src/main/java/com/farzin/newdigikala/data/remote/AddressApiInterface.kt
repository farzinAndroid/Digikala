package com.farzin.newdigikala.data.remote

import com.farzin.newdigikala.data.model.ResponseResult
import com.farzin.newdigikala.data.model.address.UserAddress
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AddressApiInterface {

    @GET("v1/getUserAddress")
    suspend fun getUserAddressList(
        @Query("token") token: String,
    ): Response<ResponseResult<List<UserAddress>>>


}