package com.example.hardwarestore.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {
    @Query("SELECT * from Products")
    fun getAllProduct(): LiveData<List<Products>>

    @Query("SELECT * from Products WHERE id = :id")
    fun getProductForBasket(id: Int): LiveData<MutableList<Products>>?

    @Query("SELECT * from Products WHERE categoryId = :id")
    fun getProductByCategory(id: Int): LiveData<List<Products>>

    @Insert
    fun insertProduct(products: Products)

    @Query("SELECT * FROM Products WHERE nameProduct LIKE '%' || :query || '%';")
    fun search(query: String): List<Products>
}

