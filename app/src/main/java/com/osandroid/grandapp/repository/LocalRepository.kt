package com.osandroid.grandapp.repository

import com.osandroid.grandapp.roomDatabase.model.Albums
import com.osandroid.grandapp.roomDatabase.model.Photos
import com.osandroid.grandapp.roomDatabase.model.User

interface LocalRepository {

    suspend fun fetchAllUserData(): List<User>

    suspend fun addUser(user: List<User>)

    suspend fun fetchAllPhotosData(): List<Photos>

    suspend fun addPhotos(photos: List<Photos>)

    suspend fun fetchAllAlbums(): List<Albums>

    suspend fun addAllAlbums(albums: List<Albums>)

    suspend fun search(search: String?): List<Photos>

}