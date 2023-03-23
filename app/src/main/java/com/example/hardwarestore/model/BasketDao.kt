package com.example.hardwarestore.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface BasketDao {
    @Query("SELECT * FROM basket where userId =:id")
    fun getBasket(id:Int): Basket
    @Insert
    fun insert(basket: Basket)
    @Delete
    fun delete(basket: Basket)
}