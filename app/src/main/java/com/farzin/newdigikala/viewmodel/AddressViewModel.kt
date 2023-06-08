package com.farzin.newdigikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.farzin.newdigikala.data.model.address.UserAddress
import com.farzin.newdigikala.data.remote.NetworkResult
import com.farzin.newdigikala.repository.AddressRepository
import com.farzin.newdigikala.util.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(private val repository: AddressRepository) :
    ViewModel() {

    val userAddressList =
        MutableStateFlow<NetworkResult<List<UserAddress>>>(NetworkResult.Loading())


    init {
        getUserAddressList(Constants.USER_TOKEN)
    }

    private fun getUserAddressList(token: String) {
        viewModelScope.launch {
            userAddressList.emit(repository.getUserAddressList(token))
        }
    }

}