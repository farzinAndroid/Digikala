package com.farzin.newdigikala.repository

import com.farzin.newdigikala.data.model.profile.LoginRequest
import com.farzin.newdigikala.data.model.profile.LoginResponse
import com.farzin.newdigikala.data.remote.BaseApiResponse
import com.farzin.newdigikala.data.remote.NetworkResult
import com.farzin.newdigikala.data.remote.ProfileApiInterface
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val api: ProfileApiInterface) :
    BaseApiResponse() {

    suspend fun login(loginRequest: LoginRequest): NetworkResult<LoginResponse> =
        safeApiCall {
            api.login(loginRequest)
        }


}