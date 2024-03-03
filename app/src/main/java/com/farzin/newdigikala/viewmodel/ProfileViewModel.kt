package com.farzin.newdigikala.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.farzin.newdigikala.data.model.profile.LoginRequest
import com.farzin.newdigikala.data.model.profile.LoginResponse
import com.farzin.newdigikala.data.model.profile.SetUserNameRequest
import com.farzin.newdigikala.data.remote.NetworkResult
import com.farzin.newdigikala.repository.ProfileRepository
import com.farzin.newdigikala.ui.screens.profile.ProfileScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repo: ProfileRepository) :
    ViewModel() {
    //sharedViewModel
    var screenState by mutableStateOf(ProfileScreenState.LOGIN_STATE)

    var inputPhoneState by mutableStateOf("")
    var inputPasswordState by mutableStateOf("")
    var loadingState by mutableStateOf(false)

    val loginResponse = MutableStateFlow<NetworkResult<LoginResponse>>(NetworkResult.Loading())

    fun login() {
        viewModelScope.launch {
            loadingState = true
            val loginRequest = LoginRequest(inputPhoneState, inputPasswordState)
            loginResponse.emit(repo.login(loginRequest))
        }
    }


    fun refreshToken(phone: String, password: String) {
        viewModelScope.launch {
            val loginRequest = LoginRequest(phone, password)
            loginResponse.emit(repo.login(loginRequest))
        }
    }


    val setUserNameResponse = MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading())
    fun setUserName(setUserNameRequest: SetUserNameRequest){
        viewModelScope.launch {
            setUserNameResponse.emit(repo.setUserName(setUserNameRequest))
        }
    }



}