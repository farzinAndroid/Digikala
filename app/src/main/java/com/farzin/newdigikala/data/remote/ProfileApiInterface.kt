package com.farzin.newdigikala.data.remote

import com.farzin.newdigikala.data.model.ResponseResult
import com.farzin.newdigikala.data.model.profile.LoginRequest
import com.farzin.newdigikala.data.model.profile.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ProfileApiInterface {

    @POST("login")
    suspend fun login(
        @Body login: LoginRequest,
    ): Response<ResponseResult<LoginResponse>>


}