package com.example.my.project.paginationandroid.app

import android.app.Application
import com.example.my.project.paginationandroid.repositories.local.AppDatabase
import com.example.my.project.paginationandroid.repositories.local.LocalInjector

class DogApp : Application() {

    override fun onCreate() {
        super.onCreate()

        LocalInjector.appDatabase = AppDatabase.getInstance(this@DogApp)

    }
}