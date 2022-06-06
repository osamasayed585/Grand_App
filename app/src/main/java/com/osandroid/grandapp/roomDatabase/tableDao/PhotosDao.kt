package com.osandroid.grandapp.roomDatabase.tableDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.osandroid.grandapp.roomDatabase.model.Photos

@Dao
interface PhotosDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPhotos(photos: List<Photos>)

    @Query("SELECT * FROM Photos")
    suspend fun fetchAllPhotosData(): List<Photos>


}