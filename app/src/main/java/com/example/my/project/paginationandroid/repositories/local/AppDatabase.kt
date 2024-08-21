package com.example.my.project.paginationandroid.repositories.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.my.project.paginationandroid.model.ImagesModel


@Database(version = 1, entities = [ImagesModel::class, RemoteKeys::class], exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getRepoDao(): RemoteKeysDao
    abstract fun getDoggoImageModelDao(): DoggoImageModelDao

    companion object {

        val DOGGO_DB = "doggo.db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DOGGO_DB)
                .build()
    }

}