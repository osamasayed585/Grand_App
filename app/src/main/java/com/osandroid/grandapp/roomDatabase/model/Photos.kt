package com.osandroid.grandapp.roomDatabase.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Photos(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var albumId: Int,
    var title: String,
    var url: String,
    var thumbnailUrl: String
)