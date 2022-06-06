package com.osandroid.grandapp.roomDatabase.tableDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.osandroid.grandapp.roomDatabase.model.Albums

@Dao
interface AlbumsDao {

    @Query("SELECT * FROM Albums")
    suspend fun fetchAllAlbums(): List<Albums>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllAlbums(albums: List<Albums>)
}