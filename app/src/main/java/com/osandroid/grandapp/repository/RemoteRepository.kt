package com.osandroid.grandapp.repository

import com.osandroid.grandapp.roomDatabase.model.Albums
import com.osandroid.grandapp.roomDatabase.model.Photos
import com.osandroid.grandapp.roomDatabase.model.User
import retrofit2.Response
import retrofit2.http.Query

interface RemoteRepository {


    suspend fun requestUser(id: Int): Response<List<User>>


    suspend fun requestAlbums(id: Int): Response<List<Albums>>


    suspend fun requestPhotos(id: Int): Response<List<Photos>>
}