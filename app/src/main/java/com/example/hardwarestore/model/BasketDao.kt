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
    @Query("Select * from basket where userId = :id")
    fun getAllProductBasket(id:Int):LiveData<List<Basket>>
    @Insert
    fun insert(basket: Basket)
    @Delete
    fun delete(basket: Basket)
    @Query("DELETE FROM basket WHERE productId = :productId and userId = :userId")
    fun deleteHistoryById(productId:Int,userId:Int)
}