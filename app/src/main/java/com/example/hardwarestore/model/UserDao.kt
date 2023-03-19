package com.example.hardwarestore.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM Users WHERE id = :id")
    fun getUser(id:Int):Users

    @Query("SELECT id FROM Users WHERE numberTelefon = :number")
    fun getIdUser(number:String):Int

    @Insert
    fun insertUser(users: Users)
}