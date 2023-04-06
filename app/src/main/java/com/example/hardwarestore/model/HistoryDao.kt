package com.example.hardwarestore.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface HistoryDao {
        @Query("Select p.* from products as p, history as h where h.productId = p.id and h.userId = :userId")
        fun getProductForBasket(userId:Int): LiveData<List<Products>>
        @Insert
        fun insert(history: History)
        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insertBasketToHistory(newData:List<History>)






















}