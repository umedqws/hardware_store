package com.example.hardwarestore.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class Users(
   @ColumnInfo val firstName:String?,
   @ColumnInfo val lastname:String?,
   @ColumnInfo val nickName:String?,
   @ColumnInfo val password:String?,
   @ColumnInfo val numberPhone:String?,
   @PrimaryKey(autoGenerate = true) val id:Int = 0,

):Parcelable {
   constructor(parcel: Parcel) : this(
      parcel.readString(),
      parcel.readString(),
      parcel.readString(),
      parcel.readString(),
      parcel.readString(),
      parcel.readInt()
   ) {
   }

   override fun writeToParcel(parcel: Parcel, flags: Int) {
      parcel.writeString(firstName)
      parcel.writeString(lastname)
      parcel.writeString(nickName)
      parcel.writeString(password)
      parcel.writeString(numberPhone)
      parcel.writeInt(id)
   }

   override fun describeContents(): Int {
      return 0
   }

   companion object CREATOR : Parcelable.Creator<Users> {
      override fun createFromParcel(parcel: Parcel): Users {
         return Users(parcel)
      }

      override fun newArray(size: Int): Array<Users?> {
         return arrayOfNulls(size)
      }
   }
}