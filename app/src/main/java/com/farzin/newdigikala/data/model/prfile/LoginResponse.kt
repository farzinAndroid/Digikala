package com.farzin.newdigikala.data.model.prfile

data class LoginResponse(
    val phone: String,
    val id: String,
    val role: String,
    val token: String,
)
