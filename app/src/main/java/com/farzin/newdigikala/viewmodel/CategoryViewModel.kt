package com.farzin.newdigikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.farzin.newdigikala.data.model.category.SubCategory
import com.farzin.newdigikala.data.remote.NetworkResult
import com.farzin.newdigikala.repository.CategoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository: CategoryRepository) :
    ViewModel() {

    val subCategory = MutableStateFlow<NetworkResult<SubCategory>>(NetworkResult.Loading())


    suspend fun getAllDataFromServer() {
        viewModelScope.launch {

            //fire and forget
            launch {
                subCategory.emit(repository.getSubCategories())
            }


        }
    }

}