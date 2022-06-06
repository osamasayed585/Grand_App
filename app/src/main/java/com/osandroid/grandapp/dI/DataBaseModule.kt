package com.osandroid.grandapp.dI

import android.app.Application
import androidx.room.Room
import com.osandroid.grandapp.roomDatabase.tableDao.AlbumsDao
import com.osandroid.grandapp.roomDatabase.tableDao.UserDao

import com.osandroid.grandapp.roomDatabase.db.AppDatabase
import com.osandroid.grandapp.roomDatabase.tableDao.PhotosDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private const val DATABASE_NAME = "GRAND_APP"

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            DATABASE_NAME
        )
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    @Singleton
    fun provideAlbumDao(appDatabase: AppDatabase): AlbumsDao {
        return appDatabase.albumDao()
    }

    @Provides
    @Singleton
    fun providePhotoDao(appDatabase: AppDatabase): PhotosDao {
        return appDatabase.photosDao()
    }


}