package com.example.hardwarestore.model

import androidx.room.Dao
import androidx.room.Query

@Dao
interface ProductDao {
@Query("SELECT * from Products")
fun getAllProduct():List<Products>
@Query("SELECT * from Products WHERE categoryId = :id")
fun getProductByCategory(id:Int):Products
}