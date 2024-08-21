package com.example.my.project.paginationandroid.view.loader

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.my.project.paginationandroid.data.ImagesRepository
import com.example.my.project.paginationandroid.model.ImagesModel
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class LoaderViewModel(val repository: ImagesRepository = ImagesRepository.getInstance()) :
    ViewModel() {


    fun fetchDoggoImages(): Flow<PagingData<ImagesModel>> {
        return repository.letDoggoImagesFlow().cachedIn(viewModelScope)
    }

}