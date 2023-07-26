package com.farzin.newdigikala.data.model.product_detail

data class NewComment(
    val description: String,
    val productId: String,
    val star: Int,
    val title: String,
    val token: String,
    val userName: String
)
