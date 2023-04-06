package com.example.hardwarestore.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class History(
    val productId: Int?,
    val userId: Int?,
    @PrimaryKey(autoGenerate = true) val id:Int = 0
) {

}