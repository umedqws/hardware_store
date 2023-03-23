package com.example.hardwarestore.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.hardwarestore.model.Basket
import com.example.hardwarestore.model.DBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BasketViewModel(app: Application): AndroidViewModel(app) {
    private val database= DBase.getInstance(app).basketDao()

    fun getBasketProduct(id:Int):Basket = database.getBasket(id)

    fun insert(
        productId: Int,
        userId:Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        database.insert(Basket(productId,userId))
    }



}