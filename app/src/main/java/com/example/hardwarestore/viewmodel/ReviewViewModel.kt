package com.example.hardwarestore.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.hardwarestore.model.DBase
import com.example.hardwarestore.model.Review
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReviewViewModel(app:Application): AndroidViewModel(app) {
    val database = DBase.getInstance(app).reviewDao()

    fun getImage(productId: Int) = database.getReview(productId)

    fun insert(rating: Int,comment:String,userName:String,productId:Int) = viewModelScope.launch(Dispatchers.IO) {
        database.insert(Review(rating, comment,userName,productId))
    }

}