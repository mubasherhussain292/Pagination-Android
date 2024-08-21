package com.example.my.project.paginationandroid.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.my.project.paginationandroid.data.ImagesRepository.Companion.DEFAULT_PAGE_INDEX
import com.example.my.project.paginationandroid.model.ImagesModel
import com.example.my.project.paginationandroid.repositories.local.AppDatabase
import com.example.my.project.paginationandroid.repositories.local.RemoteKeys
import com.example.my.project.paginationandroid.repositories.remote.ApiService
import retrofit2.HttpException
import java.io.IOException
import java.io.InvalidObjectException

@ExperimentalPagingApi
class DogMediator(val apiString: ApiService, val database: AppDatabase) : RemoteMediator<Int, ImagesModel>() {


    override suspend fun load(loadType: LoadType, state: PagingState<Int, ImagesModel>): MediatorResult {

        val pageKeyData = getKeyPageData(loadType, state)

        val page = when (pageKeyData) {
            is MediatorResult.Success -> {
                return pageKeyData
            }

            else -> {
                pageKeyData as Int
            }
        }


        try {
            val response = apiString.getAllImages(page, state.config.pageSize)
            val isEndOfList = response.isEmpty()
            database.withTransaction {

                if (loadType == LoadType.REFRESH) {
                    database.getRepoDao().clearRemoteKeys()
                    database.getDoggoImageModelDao().clearAllDoggos()
                }

                val prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1

                val nextKey = if (isEndOfList) null else page + 1

                val keys = response.map {
                    RemoteKeys(repoId = it.id, prevKey = prevKey, nextKey = nextKey)
                }

                database.getRepoDao().insertAll(keys)
                database.getDoggoImageModelDao().insertAll(response)
            }
            return MediatorResult.Success(endOfPaginationReached = isEndOfList)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }


    }

    private suspend fun getKeyPageData(loadType: LoadType, state: PagingState<Int, ImagesModel>): Any? {
        return when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getClosestRemoteKey(state)
                remoteKeys?.nextKey?.minus(1) ?: DEFAULT_PAGE_INDEX
            }

            LoadType.APPEND -> {
                val remoteKeys = getLastRemoteKey(state) ?: throw InvalidObjectException("Remote key should not be null for $loadType")
                remoteKeys.nextKey


            }

            LoadType.PREPEND -> {

                val remoteKeys = getFirstRemoteKey(state)
                    ?: throw InvalidObjectException("Invalid state, key should not be null")
                //end of list condition reached
                remoteKeys.prevKey ?: return MediatorResult.Success(endOfPaginationReached = true)
                remoteKeys.prevKey

            }
        }
    }


    private suspend fun getLastRemoteKey(state: PagingState<Int, ImagesModel>): RemoteKeys? {
        return state.pages.lastOrNull() {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { database.getRepoDao().remoteKeysDoggoId(it.id) }
    }

    private suspend fun getFirstRemoteKey(state: PagingState<Int, ImagesModel>): RemoteKeys? {
        return state.pages
            .firstOrNull() { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { doggo -> database.getRepoDao().remoteKeysDoggoId(doggo.id) }
    }


    private suspend fun getClosestRemoteKey(state: PagingState<Int, ImagesModel>): RemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { repoId ->
                database.getRepoDao().remoteKeysDoggoId(repoId)
            }
        }
    }

}