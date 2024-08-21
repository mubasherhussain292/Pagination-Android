package com.example.my.project.paginationandroid.repositories.remote

import com.example.my.project.paginationandroid.model.ImagesModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v1/images/search")
    suspend fun getAllImages(@Query("page") page: Int, @Query("limit") size: Int): List<ImagesModel>
}