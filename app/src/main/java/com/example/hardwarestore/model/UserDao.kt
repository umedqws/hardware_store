package com.example.hardwarestore.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import java.net.PasswordAuthentication

@Dao
interface UserDao {
    @Query("SELECT * FROM Users")
    fun getAllUser():LiveData<List<Users>>

    @Query("SELECT * FROM Users WHERE id = :id")
    fun getUserByID(id:Int):Users

    @Query("SELECT * FROM Users where numberPhone= :number and password= :password")
    fun getUser(number: String, password: String):LiveData<Users>

    @Query("SELECT id FROM Users WHERE numberPhone = :number")
    fun getIdUser(number:String):Int

    @Insert
    fun insertUser(users: Users)
    @Delete
    fun delete(users: Users)
}