package com.farzin.newdigikala.data.model.purchase

data class PaymentVerificationRequest(
    val amount: Long,
    val authority: String,
    val merchant_id: String
)