package com.example.hardwarestore.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TimeDao {
    @Query("Select * from UserTime")
    fun getUserTime():LiveData<List<UserTime>>
    @Insert
    fun insertTimeUser(userTime: UserTime)
    @Delete
    fun delete(userTime: UserTime)

}