package com.example.hardwarestore.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ReviewDao {

    @Query("Select * from review where productId = :id")
    fun getReview(id:Int):LiveData<List<Review>>
    @Insert
    fun insert(review: Review)

}