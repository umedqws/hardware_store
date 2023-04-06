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

    @Query("SELECT * FROM Users WHERE id = :userId")
    fun getIdUser(userId:Int):LiveData<Users>

    @Query("Select imageUser from Users where id = :id")
    fun getImage(id:Int):LiveData<String>

    @Query("Update Users set imageUser =:image where id = :userId")
    fun update(image:String,userId:Int)

    @Insert
    fun insertUser(users: Users)
    @Delete
    fun delete(users: Users)
}