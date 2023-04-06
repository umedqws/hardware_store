package com.example.hardwarestore.model

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "review")
data class Review(
    val rating: Int,
    val comment:String,
    val userName:String,
    val productId:Int,
    @PrimaryKey(autoGenerate = true) val id:Int = 0
)