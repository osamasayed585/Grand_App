package com.osandroid.grandapp.roomDatabase.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import javax.inject.Inject

@Entity
data class Albums @Inject constructor(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var userId: Int,
    var title: String
)