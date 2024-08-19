package com.farzin.newdigikala.data.remote

import com.farzin.newdigikala.data.model.ResponseResult
import com.farzin.newdigikala.data.model.address.AddAddressRequest
import com.farzin.newdigikala.data.model.address.UserAddress
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AddressApiInterface {

    @GET("getUserAddress")
    suspend fun getUserAddressList(
        @Query("token") token: String,
    ): Response<ResponseResult<List<UserAddress>>>



    @POST("saveUserAddress")
    suspend fun saveUserAddress(
        @Body addAddressRequest: AddAddressRequest
    ): Response<ResponseResult<String>>


}