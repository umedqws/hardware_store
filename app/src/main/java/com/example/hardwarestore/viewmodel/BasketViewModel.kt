package com.example.hardwarestore.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.hardwarestore.model.Basket
import com.example.hardwarestore.model.DBase
import com.example.hardwarestore.model.Products
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BasketViewModel(app: Application): AndroidViewModel(app) {
    private val database= DBase.getInstance(app).basketDao()



    fun getProductsForBasket(userId:Int):LiveData<List<Products>> =  database.getProductForBasket(userId)

    fun delete(productId: Int,userId: Int) = viewModelScope.launch(Dispatchers.IO) {
        database.deleteHistoryById(productId,userId)
    }

    fun getAll(id:Int):LiveData<List<Basket>> = database.getAllProductBasket(id)



    fun insert(
        productId: Int,
        userId:Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        database.insert(Basket(productId,userId))
    }



}