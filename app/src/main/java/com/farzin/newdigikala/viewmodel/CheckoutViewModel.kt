package com.farzin.newdigikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farzin.newdigikala.data.model.checkout.OrderDetails
import com.farzin.newdigikala.data.remote.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import com.farzin.newdigikala.repository.CheckoutRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(private val repository: CheckoutRepository) :
    ViewModel() {

    val shippingCost = MutableStateFlow<NetworkResult<Int>>(NetworkResult.Loading())
    val orderResponse = MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading())

    fun getShippingCost(address: String) {
        viewModelScope.launch(Dispatchers.IO) {
            shippingCost.emit(repository.getShippingCost(address))
        }
    }

    fun setNewOrder(orderDetails: OrderDetails) {
        viewModelScope.launch(Dispatchers.IO) {
            orderResponse.emit(repository.setNewOrder(orderDetails))
        }
    }


}