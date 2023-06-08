package com.farzin.newdigikala.repository

import com.farzin.newdigikala.data.remote.*
import javax.inject.Inject

class CheckoutRepository @Inject constructor(private val api: CheckoutApiInterface) : BaseApiResponse() {

    /*suspend fun login(loginRequest: LoginRequest): NetworkResult<LoginResponse> =
        safeApiCall {
           api.login(loginRequest)
        }*/


}