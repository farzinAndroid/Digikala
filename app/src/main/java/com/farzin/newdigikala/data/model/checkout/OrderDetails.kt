package com.farzin.newdigikala.data.model.checkout

import com.farzin.newdigikala.data.model.basket.CartItem

data class OrderDetails(
    val token: String,
    val orderAddress: String,
    val orderTotalDiscount: Long,
    val orderTotalPrice: Long,
    val orderUserName: String,
    val orderUserPhone: String,
    val orderProducts: List<CartItem>,

    )
