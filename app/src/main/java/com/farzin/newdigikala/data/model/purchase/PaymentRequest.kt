package com.farzin.newdigikala.data.model.purchase

data class PaymentRequest(
    val amount: Long,
    val callback_url: String,
    val description: String,
    val merchant_id: String
)