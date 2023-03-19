package com.example.hardwarestore.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CategoryDao {
    @Query("SELECT * from categories")
    fun getCategory(): LiveData<List<Categories>>
    @Insert
    fun insertCategory(categories: Categories)
}