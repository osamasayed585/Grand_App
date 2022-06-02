package com.osandroid.grandapp.restfulApi

import com.osandroid.grandapp.roomDatabase.model.Albums
import com.osandroid.grandapp.roomDatabase.model.Photos
import com.osandroid.grandapp.roomDatabase.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/users")
    suspend fun requestUser(@Query("id") id: Int): Response<List<User>>

    @GET("/albums")
    suspend fun requestAlbums(@Query("userId") id: Int): Response<List<Albums>>

    @GET("/photos")
    suspend fun requestPhotos(@Query("albumId") id: Int): Response<List<Photos>>
}