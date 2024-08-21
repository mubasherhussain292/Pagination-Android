package com.example.my.project.paginationandroid.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.my.project.paginationandroid.data.ImagesRepository.Companion.DEFAULT_PAGE_INDEX
import com.example.my.project.paginationandroid.model.ImagesModel
import com.example.my.project.paginationandroid.repositories.remote.ApiService
import retrofit2.HttpException
import java.io.IOException

@ExperimentalPagingApi
class DoggoImagePagingSource(val doggoApiService: ApiService) :
    PagingSource<Int, ImagesModel>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ImagesModel> {
        //for first case it will be null, then we can pass some default value, in our case it's 1
        val page = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val response = doggoApiService.getAllImages(page, params.loadSize)
            LoadResult.Page(
                response, prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ImagesModel>): Int? {
        TODO("Not yet implemented")
    }

}