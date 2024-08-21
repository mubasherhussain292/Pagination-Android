package com.example.my.project.paginationandroid.view.remote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.my.project.paginationandroid.data.ImagesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@ExperimentalPagingApi
class RemoteViewModel(val repository: ImagesRepository = ImagesRepository.getInstance()) : ViewModel() {


    fun fetchDoggoImages(): Flow<PagingData<String>> = repository.letDoggoImagesFlow()
        .map { it.map { it.url } }
        .cachedIn(viewModelScope)
}