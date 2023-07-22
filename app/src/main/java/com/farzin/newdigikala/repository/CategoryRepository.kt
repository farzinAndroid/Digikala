package com.farzin.newdigikala.repository

import com.farzin.newdigikala.data.model.category.SubCategory
import com.farzin.newdigikala.data.remote.BaseApiResponse
import com.farzin.newdigikala.data.remote.CategoryApiInterface
import com.farzin.newdigikala.data.remote.NetworkResult
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val api: CategoryApiInterface) :
    BaseApiResponse() {

    suspend fun getSubCategories(): NetworkResult<SubCategory> =
        safeApiCall {
            api.getSubCategories()
        }

}