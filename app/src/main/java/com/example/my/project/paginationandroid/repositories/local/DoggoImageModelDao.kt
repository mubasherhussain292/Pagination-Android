package com.example.my.project.paginationandroid.repositories.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.my.project.paginationandroid.model.ImagesModel

@Dao
interface DoggoImageModelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(doggoModel: List<ImagesModel>)

    @Query("SELECT * FROM imagesmodel")
    fun getAllDoggoModel(): PagingSource<Int, ImagesModel>

    @Query("DELETE FROM imagesmodel")
    suspend fun clearAllDoggos()

}