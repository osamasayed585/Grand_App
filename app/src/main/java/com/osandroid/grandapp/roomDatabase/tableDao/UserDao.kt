package com.osandroid.grandapp.roomDatabase.tableDao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.osandroid.grandapp.roomDatabase.model.User

@Dao
interface UserDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: List<User>)


    @Query("SELECT * FROM user")
    suspend fun fetchAllUserData(): List<User>


}