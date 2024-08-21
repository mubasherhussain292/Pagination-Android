package com.example.my.project.paginationandroid.repositories.local

object LocalInjector {

    var appDatabase: AppDatabase? = null

    fun injectDb(): AppDatabase? {
        return appDatabase
    }
}