package com.farzin.newdigikala.repository

import com.farzin.newdigikala.data.model.address.UserAddress
import com.farzin.newdigikala.data.model.product_detail.ProductDetail
import com.farzin.newdigikala.data.remote.*
import javax.inject.Inject

class ProductDetailRepository @Inject constructor(private val api: ProductDetailApiInterface) : BaseApiResponse() {

    suspend fun getProductById(id: String): NetworkResult<ProductDetail> =
        safeApiCall {
          api.getProductById(id)
        }


}