package com.farzin.newdigikala.repository

import com.farzin.newdigikala.data.model.address.UserAddress
import com.farzin.newdigikala.data.remote.*
import javax.inject.Inject

class AddressRepository @Inject constructor(private val api: AddressApiInterface) : BaseApiResponse() {

    suspend fun getUserAddressList(token: String): NetworkResult<List<UserAddress>> =
        safeApiCall {
          api.getUserAddressList(token)
        }


}