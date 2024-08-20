package com.farzin.newdigikala.data.model.purchase

data class PaymentVerificationResponse(
    val data: PaymentVerificationData,
    val errors: Errors
)

data class PaymentVerificationData(
    val wages: List<Any>,
    val code: Int,
    val message: String,
    val card_hash: String,
    val card_pan: String,
    val ref_id: Long,
    val fee_type: String,
    val fee: Int,
    val shaparak_fee: String,
    val order_id: String
)


data class Errors(
    val code: Int,
    val message: String,
    val validations: List<Any>
)