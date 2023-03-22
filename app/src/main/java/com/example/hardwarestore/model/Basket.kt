package com.example.hardwarestore.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "basket")
data class Basket(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    val nameProduct: String?,
    val descriptionProduct: String?,
    val price: Int?,
    val aboutProduct: String?,
    val categoryId: Int,
    val image: Int?,
    val userId: Int
)