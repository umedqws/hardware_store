package com.example.hardwarestore.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BasketDao {
    @Query("Select p.* from products as p, basket as b where b.productId = p.id and b.userId = :userId")
    fun getProductForBasket(userId:Int): LiveData<List<Products>>
    @Insert
    fun insert(basket: Basket)
    @Delete
    fun delete(basket: Basket)
}