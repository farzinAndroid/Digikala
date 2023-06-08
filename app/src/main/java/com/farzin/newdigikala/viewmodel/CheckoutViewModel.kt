package com.farzin.newdigikala.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import com.farzin.newdigikala.repository.CheckoutRepository
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(private val repository: CheckoutRepository) :
    ViewModel() {


}