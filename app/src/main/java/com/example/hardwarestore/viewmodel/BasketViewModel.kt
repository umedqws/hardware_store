package com.example.hardwarestore.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.hardwarestore.model.Basket
import com.example.hardwarestore.model.DBase
import com.example.hardwarestore.model.Products
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BasketViewModel(app: Application): AndroidViewModel(app) {
    private val database= DBase.getInstance(app).basketDao()

    fun insert(
        name: String,
        des: String,
        price: Int,
        about: String,
        category: Int,
        image: Int,
        userId:Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        database.insertBasket(Basket(0, name, des, price, about, category, image,userId))
    }

}