package com.farzin.newdigikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.farzin.newdigikala.data.model.address.AddAddressRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import com.farzin.newdigikala.data.model.address.UserAddress
import com.farzin.newdigikala.data.remote.NetworkResult
import com.farzin.newdigikala.repository.AddressRepository
import com.farzin.newdigikala.util.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(private val repo: AddressRepository) :
    ViewModel() {

    val userAddressList =
        MutableStateFlow<NetworkResult<List<UserAddress>>>(NetworkResult.Loading())


    val addNewAddressResponse =
        MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading())



    fun getUserAddressList(token: String) {
        viewModelScope.launch {
            userAddressList.emit(repo.getUserAddressList(token))
        }
    }

    fun saveUserAddress(addAddressRequest: AddAddressRequest) {
        viewModelScope.launch {
            addNewAddressResponse.emit(repo.saveUserAddress(addAddressRequest))
        }
    }

}