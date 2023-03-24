package com.example.hardwarestore.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "basket")
data class Basket(
    val productId: Int?,
    val userId: Int?,
    @PrimaryKey(autoGenerate = true) val id:Int = 0
) {

}