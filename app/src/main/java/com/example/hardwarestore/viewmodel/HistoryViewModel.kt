package com.example.hardwarestore.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.hardwarestore.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HistoryViewModel (app: Application): AndroidViewModel(app) {
    private val database = DBase.getInstance(app).historyDao()

    fun getHistoryByProductForHistory(id:Int):LiveData<List<Products>> = database.getProductForBasket(id)



    fun insertHistory(
        productId: Int,
        userId:Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        database.insert(History(productId,userId))
    }

}