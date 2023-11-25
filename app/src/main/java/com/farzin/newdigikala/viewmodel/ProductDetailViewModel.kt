package com.farzin.newdigikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import com.farzin.newdigikala.data.model.address.UserAddress
import com.farzin.newdigikala.data.model.home.StoreProduct
import com.farzin.newdigikala.data.model.product_detail.Comment
import com.farzin.newdigikala.data.model.product_detail.NewComment
import com.farzin.newdigikala.data.model.product_detail.ProductDetail
import com.farzin.newdigikala.data.remote.NetworkResult
import com.farzin.newdigikala.data.source.ProductCommentDataSource
import com.farzin.newdigikala.repository.AddressRepository
import com.farzin.newdigikala.repository.ProductDetailRepository
import com.farzin.newdigikala.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(private val repo: ProductDetailRepository) :
    ViewModel() {

    val productDetails =
        MutableStateFlow<NetworkResult<ProductDetail>>(NetworkResult.Loading())

    val similarProducts =
        MutableStateFlow<NetworkResult<List<StoreProduct>>>(NetworkResult.Loading())


    val newCommentResult =
        MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading())


    fun getProductById(id: String) {
        viewModelScope.launch {
            productDetails.emit(repo.getProductById(id))
        }
    }

    fun getSimilarProducts(categoryId: String) {
        viewModelScope.launch {
            similarProducts.emit(repo.getSimilarProducts(categoryId))
        }
    }

    fun setNewComment(newComment: NewComment) {
        viewModelScope.launch {
            newCommentResult.emit(repo.setNewComment(newComment))
        }
    }


    var commentsList: Flow<PagingData<Comment>> = flow { emit(PagingData.empty()) }
    fun getAllProductComments(productId:String) {
        commentsList = Pager(
            PagingConfig(5)
        ){
            ProductCommentDataSource(repo,productId)
        }.flow.cachedIn(viewModelScope)
    }

}