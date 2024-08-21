package com.example.my.project.paginationandroid.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImagesModel(@PrimaryKey val id: String, val url: String)
