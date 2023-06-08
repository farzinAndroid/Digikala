package com.farzin.newdigikala.data.remote

import com.farzin.newdigikala.data.model.ResponseResult
import com.farzin.newdigikala.data.model.category.SubCategory
import retrofit2.Response
import retrofit2.http.GET

interface CategoryApiInterface {

    @GET("v1/getSubCategories")
    suspend fun getSubCategories() : Response<ResponseResult<SubCategory>>

}