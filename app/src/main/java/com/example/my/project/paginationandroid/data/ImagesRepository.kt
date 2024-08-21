package com.example.my.project.paginationandroid.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.my.project.paginationandroid.model.ImagesModel
import com.example.my.project.paginationandroid.repositories.local.AppDatabase
import com.example.my.project.paginationandroid.repositories.local.LocalInjector
import com.example.my.project.paginationandroid.repositories.remote.ApiService
import com.example.my.project.paginationandroid.repositories.remote.RemoteInjector
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class ImagesRepository(
    val apiService: ApiService = RemoteInjector.injectApiService(),
    val appDatabase: AppDatabase? = LocalInjector.injectDb()
) {

    companion object {
        const val DEFAULT_PAGE_INDEX = 1
        const val DEFAULT_PAGE_SIZE = 1


        fun getInstance() = ImagesRepository()
    }

    fun letDoggoImagesFlow(pagingConfig: PagingConfig = getDefaultPageConfig()): Flow<PagingData<ImagesModel>> {
        return Pager(
            config = pagingConfig,
            pagingSourceFactory = { DoggoImagePagingSource(apiService) }
        ).flow
    }


    private fun getPageConfigDefault(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true)
    }


    private fun getDefaultPageConfig(): PagingConfig {
        return PagingConfig(pageSize = DEFAULT_PAGE_SIZE, enablePlaceholders = true)
    }

    fun dogImagesDb(pagingConfig: PagingConfig = getPageConfigDefault()): Flow<PagingData<ImagesModel>> {
        if (appDatabase == null) throw IllegalStateException("Database is not initailized")
        val pagingSourceFactory = { appDatabase.getDoggoImageModelDao().getAllDoggoModel() }

        return Pager(
            config = pagingConfig,
            pagingSourceFactory = pagingSourceFactory,
            remoteMediator = DogMediator(apiService, database = appDatabase)
        ).flow

    }


}