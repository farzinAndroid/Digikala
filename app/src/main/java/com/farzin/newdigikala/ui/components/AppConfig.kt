package com.farzin.newdigikala.ui.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.farzin.newdigikala.data.remote.NetworkResult
import com.farzin.newdigikala.util.Constants.USER_ID
import com.farzin.newdigikala.util.Constants.USER_LANGUAGE
import com.farzin.newdigikala.util.Constants.USER_NAME
import com.farzin.newdigikala.util.Constants.USER_PASSWORD
import com.farzin.newdigikala.util.Constants.USER_PHONE
import com.farzin.newdigikala.util.Constants.USER_TOKEN
import com.farzin.newdigikala.viewmodel.DataStoreViewModel
import com.farzin.newdigikala.viewmodel.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AppConfig(
    profileViewModel: ProfileViewModel = hiltViewModel(),
    dataStore: DataStoreViewModel = hiltViewModel(),
) {

    getDataStoreVariables(dataStore)

    profileViewModel.refreshToken(USER_PHONE, USER_PASSWORD)


    LaunchedEffect(Dispatchers.Main) {
        profileViewModel.loginResponse.collectLatest { loginResponse ->
            when (loginResponse) {
                is NetworkResult.Success -> {
                    loginResponse.data?.let { user ->
                        if (user.token.isNotEmpty()) {
                            dataStore.saveUserToken(user.token)
                            dataStore.saveUserId(user.id)
                            dataStore.saveUserPhoneNumber(user.phone)
                            dataStore.saveUserPassword(USER_PASSWORD)
                            USER_TOKEN = user.token

                            dataStore.saveUserName(user.name ?: "null")


                            getDataStoreVariables(dataStore)

                            Log.e("TAG", "refresh token")
                        }

                    }
                }

                else -> {}
            }
        }

    }

}

private fun getDataStoreVariables(dataStore: DataStoreViewModel) {
    USER_LANGUAGE = dataStore.getUserLanguage()
    dataStore.saveUserLanguage(USER_LANGUAGE)

    USER_PHONE = dataStore.getUserPhoneNumber().toString()
    USER_PASSWORD = dataStore.getUserPassword().toString()
    USER_TOKEN = dataStore.getUserToken().toString()
    USER_ID = dataStore.getUserId().toString()
    USER_NAME = dataStore.getUserName().toString()
}