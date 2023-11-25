package com.farzin.newdigikala.repository

import com.farzin.newdigikala.data.model.address.UserAddress
import com.farzin.newdigikala.data.model.home.StoreProduct
import com.farzin.newdigikala.data.model.product_detail.Comment
import com.farzin.newdigikala.data.model.product_detail.NewComment
import com.farzin.newdigikala.data.model.product_detail.ProductDetail
import com.farzin.newdigikala.data.remote.*
import javax.inject.Inject

class ProductDetailRepository @Inject constructor(private val api: ProductDetailApiInterface) :
    BaseApiResponse() {

    suspend fun getProductById(id: String): NetworkResult<ProductDetail> =
        safeApiCall {
            api.getProductById(id)
        }

    suspend fun getSimilarProducts(categoryId: String): NetworkResult<List<StoreProduct>> =
        safeApiCall {
            api.getSimilarProducts(categoryId)
        }


    suspend fun setNewComment(newComment: NewComment): NetworkResult<String> =
        safeApiCall {
            api.setNewComment(newComment)
        }

    suspend fun getAllProductComments(
        id: String,
        pageSize: Int,
        pageNumber: Int,
    ): NetworkResult<List<Comment>> =
        safeApiCall {
            api.getAllProductComments(id, pageSize, pageNumber)
        }


}