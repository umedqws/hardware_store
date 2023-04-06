package com.example.hardwarestore.viewmodel

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.hardwarestore.model.Users

open class ActivityViewModel(app:Application) : AndroidViewModel(app) {
   var user: Users? = null

   val sharedPreferences: SharedPreferences = app.getSharedPreferences("myPref",MODE_PRIVATE)

   val editor = sharedPreferences.edit().putString("user_id","${user?.id}")





}