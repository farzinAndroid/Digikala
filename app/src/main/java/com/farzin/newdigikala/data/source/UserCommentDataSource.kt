package com.farzin.newdigikala.data.source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.farzin.newdigikala.data.model.product_detail.Comment
import com.farzin.newdigikala.repository.CommentRepository

class UserCommentDataSource(
    private val productDetailRepository: CommentRepository,
    private val token:String
) : PagingSource<Int, Comment>() {
    override fun getRefreshKey(state: PagingState<Int, Comment>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val pageAnchor = state.closestPageToPosition(anchorPosition)
            pageAnchor?.prevKey?.plus(1) ?: pageAnchor?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Comment> {
        return try {
            val nextPage = params.key ?: 1
            val response = productDetailRepository.getUserComments(
                token = token,
                pageSize = 5,
                pageNumber = nextPage
            ).data

            LoadResult.Page(
                data = response!!,
                prevKey = null,
                nextKey = nextPage + 1
            )

        } catch (e: Exception) {
            Log.e("TAG","error $e")
            LoadResult.Error(e)
        }
    }


}