package com.osandroid.grandapp.repository

import com.osandroid.grandapp.restfulApi.ApiService
import com.osandroid.grandapp.roomDatabase.db.AppDatabase
import com.osandroid.grandapp.roomDatabase.model.Albums
import com.osandroid.grandapp.roomDatabase.model.Photos
import com.osandroid.grandapp.roomDatabase.model.User
import com.osandroid.grandapp.roomDatabase.tableDao.AlbumsDao
import com.osandroid.grandapp.roomDatabase.tableDao.UserDao
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class GrandRepository @Inject constructor(
    val apiService: ApiService,
    val db: AppDatabase
) : RemoteRepository, LocalRepository {


    override suspend fun requestUser(id: Int) = withContext(Dispatchers.IO) {
        apiService.requestUser(id)
    }

    override suspend fun requestAlbums(id: Int) = withContext(Dispatchers.IO) {
        apiService.requestAlbums(id)
    }

    override suspend fun requestPhotos(id: Int) = withContext(Dispatchers.IO) {
        apiService.requestPhotos(id)
    }

    override suspend fun fetchAllUserData() = withContext(Dispatchers.IO) {
        db.userDao().fetchAllUserData()
    }

    override suspend fun addUser(user: List<User>) {
        db.userDao().addUser(user)
    }

    override suspend fun fetchAllPhotosData() = withContext(Dispatchers.IO) {
        db.photosDao().fetchAllPhotosData()
    }

    override suspend fun addPhotos(photos: List<Photos>) {
        db.photosDao().addPhotos(photos)
    }

    override suspend fun fetchAllAlbums() = withContext(Dispatchers.IO) {
        db.albumDao().fetchAllAlbums()
    }

    override suspend fun addAllAlbums(albums: List<Albums>) {
        db.albumDao().addAllAlbums(albums)
    }

}