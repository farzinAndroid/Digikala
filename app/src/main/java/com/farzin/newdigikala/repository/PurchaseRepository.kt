package com.farzin.newdigikala.repository

import android.util.Log
import com.farzin.newdigikala.data.model.purchase.PaymentRequest
import com.farzin.newdigikala.data.model.purchase.PaymentResponse
import com.farzin.newdigikala.data.model.purchase.PaymentVerificationRequest
import com.farzin.newdigikala.data.model.purchase.PaymentVerificationResponse
import com.farzin.newdigikala.data.remote.BaseApiResponse
import com.farzin.newdigikala.data.remote.PurchaseApiInterface
import retrofit2.HttpException
import javax.inject.Inject

class PurchaseRepository @Inject constructor(private val api: PurchaseApiInterface) :
    BaseApiResponse() {

    suspend fun startPurchase(paymentRequest: PaymentRequest): PaymentResponse? {
        return try {
            val response = api.startPurchase(paymentRequest)
            if (response.isSuccessful){
                response.body()
            }else{
                throw HttpException(response)
            }
        }catch (e:Exception){
            Log.e("TAG","startPurchase: failed $e")
            throw e
        }
    }


    suspend fun verifyPurchase(paymentVerificationRequest: PaymentVerificationRequest): PaymentVerificationResponse? {
        return try {
            val response = api.verifyPurchase(paymentVerificationRequest)
            if (response.isSuccessful){
                response.body()
            }else{
                throw HttpException(response)
            }
        }catch (e:Exception){
            Log.e("TAG","verifyPurchase: failed $e")
            throw e
        }
    }


}