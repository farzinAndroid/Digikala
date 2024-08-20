package com.farzin.newdigikala.data.model.purchase

data class PaymentResponse(
    val data: Data,
    val errors: List<Any>
)

data class Data(
    val authority: String,
    val code: Int,
    val fee: Int,
    val fee_type: String,
    val message: String
)