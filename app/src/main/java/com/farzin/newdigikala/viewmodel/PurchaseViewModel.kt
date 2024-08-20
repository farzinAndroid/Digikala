package com.farzin.newdigikala.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farzin.newdigikala.data.model.purchase.PaymentRequest
import com.farzin.newdigikala.data.model.purchase.PaymentResponse
import com.farzin.newdigikala.data.model.purchase.PaymentVerificationRequest
import com.farzin.newdigikala.data.model.purchase.PaymentVerificationResponse
import com.farzin.newdigikala.repository.PurchaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PurchaseViewModel @Inject constructor(private val repo: PurchaseRepository) :
    ViewModel() {

    private val _purchaseResult = MutableStateFlow<PaymentResponse?>(null)
    val purchaseResult: StateFlow<PaymentResponse?> = _purchaseResult


    private val _verifyPurchaseResult = MutableStateFlow<PaymentVerificationResponse?>(null)
    val verifyPurchaseResult: StateFlow<PaymentVerificationResponse?> = _verifyPurchaseResult

    fun startPurchase(paymentRequest: PaymentRequest) {
        viewModelScope.launch {
            try {
                _purchaseResult.emit(repo.startPurchase(paymentRequest))
            } catch (e: Exception) {
                Log.e("TAG","exception in startPurchase: $e")
                _purchaseResult.emit(null)
            }
        }
    }


    fun verifyPurchase(paymentVerificationRequest: PaymentVerificationRequest) {
        viewModelScope.launch {
            try {
                _verifyPurchaseResult.emit(repo.verifyPurchase(paymentVerificationRequest))
            } catch (e: Exception) {
                Log.e("TAG","exception in verifyPurchase: $e")
                _verifyPurchaseResult.emit(null)
            }
        }
    }


    fun openBrowser(url:String,context: Context){
        val intent = Intent(Intent.ACTION_VIEW,Uri.parse(url))
        intent.apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

}