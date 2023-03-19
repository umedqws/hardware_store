package com.example.hardwarestore.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class Categories(
    @ColumnInfo val nameCategory:String,
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
)