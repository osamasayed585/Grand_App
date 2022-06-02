package com.osandroid.grandapp.repository

import com.osandroid.grandapp.restfulApi.ApiService
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class GrandRepository @Inject constructor(val apiService: ApiService) : RemoteRepository {


    override suspend fun requestUser(id: Int) = withContext(Dispatchers.IO) {
        apiService.requestUser(id)
    }

    override suspend fun requestAlbums(id: Int) = withContext(Dispatchers.IO) {
        apiService.requestAlbums(id)
    }

    override suspend fun requestPhotos(id: Int) = withContext(Dispatchers.IO) {
        apiService.requestPhotos(id)
    }
}