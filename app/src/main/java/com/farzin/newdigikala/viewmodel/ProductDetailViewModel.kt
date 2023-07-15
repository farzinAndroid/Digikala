package com.farzin.newdigikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.farzin.newdigikala.data.model.address.UserAddress
import com.farzin.newdigikala.data.model.product_detail.ProductDetail
import com.farzin.newdigikala.data.remote.NetworkResult
import com.farzin.newdigikala.repository.AddressRepository
import com.farzin.newdigikala.repository.ProductDetailRepository
import com.farzin.newdigikala.util.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(private val repo: ProductDetailRepository) :
    ViewModel() {

    val productDetails =
        MutableStateFlow<NetworkResult<ProductDetail>>(NetworkResult.Loading())


    fun getProductById(id: String) {
        viewModelScope.launch {
           productDetails.emit(repo.getProductById(id))
        }
    }

}