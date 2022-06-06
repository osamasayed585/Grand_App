package com.osandroid.grandapp.roomDatabase.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.osandroid.grandapp.roomDatabase.converters.Converters
import com.osandroid.grandapp.roomDatabase.model.Albums
import com.osandroid.grandapp.roomDatabase.model.Photos
import com.osandroid.grandapp.roomDatabase.model.User
import com.osandroid.grandapp.roomDatabase.tableDao.AlbumsDao
import com.osandroid.grandapp.roomDatabase.tableDao.PhotosDao
import com.osandroid.grandapp.roomDatabase.tableDao.UserDao


@Database(entities = [User::class, Albums::class, Photos::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun albumDao(): AlbumsDao

    abstract fun photosDao(): PhotosDao

}