package com.example.hardwarestore.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserTime")
data class UserTime(
    val userId: Int,
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
)