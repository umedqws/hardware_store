package com.example.hardwarestore.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Products(
@PrimaryKey(autoGenerate = true) var id:Int = 0,
@ColumnInfo val nameProduct:String?,
@ColumnInfo val descriptionProduct:String?,
@ColumnInfo val price:Int?,
@ColumnInfo val aboutProduct:String?,
@ColumnInfo val categoryId:Int?,
)