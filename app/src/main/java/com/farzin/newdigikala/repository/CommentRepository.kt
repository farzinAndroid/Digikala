package com.farzin.newdigikala.repository

import com.farzin.newdigikala.data.model.product_detail.Comment
import com.farzin.newdigikala.data.model.product_detail.NewComment
import com.farzin.newdigikala.data.remote.*
import javax.inject.Inject

class CommentRepository @Inject constructor(private val api: CommentApiInterface) :
    BaseApiResponse() {

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


    suspend fun getUserComments(
        token: String,
        pageSize: Int,
        pageNumber: Int,
    ): NetworkResult<List<Comment>> =
        safeApiCall {
            api.getUserComments(token, pageSize, pageNumber)
        }


}