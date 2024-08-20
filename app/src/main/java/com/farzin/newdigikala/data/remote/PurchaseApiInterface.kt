package com.farzin.newdigikala.data.remote

import com.farzin.newdigikala.data.model.purchase.PaymentRequest
import com.farzin.newdigikala.data.model.purchase.PaymentResponse
import com.farzin.newdigikala.data.model.purchase.PaymentVerificationRequest
import com.farzin.newdigikala.data.model.purchase.PaymentVerificationResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface PurchaseApiInterface {

    @POST("request.json")
    suspend fun startPurchase(
        @Body paymentRequest: PaymentRequest,
    ): Response<PaymentResponse>


    @POST("verify.json")
    suspend fun verifyPurchase(
        @Body paymentVerificationRequest: PaymentVerificationRequest,
    ): Response<PaymentVerificationResponse>

}