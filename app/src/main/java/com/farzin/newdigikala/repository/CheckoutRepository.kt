package com.farzin.newdigikala.repository

import com.farzin.newdigikala.data.model.checkout.OrderDetails
import com.farzin.newdigikala.data.remote.*
import javax.inject.Inject

class CheckoutRepository @Inject constructor(private val api: CheckoutApiInterface) : BaseApiResponse() {

    suspend fun getShippingCost(address: String): NetworkResult<Int> =
        safeApiCall {
           api.getShippingCost(address)
        }

    suspend fun setNewOrder(orderDetails: OrderDetails): NetworkResult<String> =
        safeApiCall {
            api.setNewOrder(orderDetails)
        }

}