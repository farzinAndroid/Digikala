package com.farzin.newdigikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.farzin.newdigikala.data.model.product_detail.Comment
import com.farzin.newdigikala.data.model.product_detail.NewComment
import com.farzin.newdigikala.data.remote.NetworkResult
import com.farzin.newdigikala.data.source.ProductCommentDataSource
import com.farzin.newdigikala.data.source.UserCommentDataSource
import com.farzin.newdigikala.repository.CommentRepository
import com.farzin.newdigikala.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(private val repo: CommentRepository) :
    ViewModel() {

    val newCommentResult =
        MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading())

    var productCommentsList: Flow<PagingData<Comment>> = flow { emit(PagingData.empty()) }
    var userCommentsList: Flow<PagingData<Comment>> = flow { emit(PagingData.empty()) }



    fun setNewComment(newComment: NewComment) {
        viewModelScope.launch {
            newCommentResult.emit(repo.setNewComment(newComment))
        }
    }



    fun getAllProductComments(productId:String) {
        productCommentsList = Pager(
            PagingConfig(5)
        ){
            ProductCommentDataSource(repo,productId)
        }.flow.cachedIn(viewModelScope)
    }

    fun getUserComments() {
        userCommentsList = Pager(
            PagingConfig(5)
        ){
            UserCommentDataSource(repo,Constants.USER_TOKEN)
        }.flow.cachedIn(viewModelScope)
    }

}