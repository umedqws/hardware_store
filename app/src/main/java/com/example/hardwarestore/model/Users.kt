package com.example.hardwarestore.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class Users(
   @PrimaryKey(autoGenerate = true) val id:Int = 0,
   @ColumnInfo val firstName:String?,
   @ColumnInfo val lastname:String?,
   @ColumnInfo val password:String?,
   @ColumnInfo val numberTelefon:String

)