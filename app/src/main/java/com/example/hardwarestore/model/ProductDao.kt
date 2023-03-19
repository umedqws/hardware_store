package com.example.hardwarestore.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {
    @Query("SELECT * from Products")
    fun getAllProduct(): LiveData<List<Products>>

    @Query("SELECT * from Products WHERE categoryId = :id")
    fun getProductByCategory(id: Int): LiveData<List<Products>>

    @Insert
    fun insertProduct(products: Products)

}

