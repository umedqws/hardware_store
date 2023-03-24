package com.example.hardwarestore.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.hardwarestore.model.Categories
import com.example.hardwarestore.model.DBase
import com.example.hardwarestore.model.Products
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductsViewModel(app: Application) : AndroidViewModel(app) {
    private val database = DBase.getInstance(app).productDao()

    fun list(): LiveData<List<Products>> = database.getAllProduct()

    private val _listBasket = MutableLiveData<List<Products>>()
    val listBasket: LiveData<List<Products>> = _listBasket

    private val _search = MutableLiveData<List<Products>>()
    val searchLiveData: LiveData<List<Products>> = _search

    fun search(query: String) =  viewModelScope.launch(Dispatchers.IO) {
        val searchingList = database.search(query)
        _search.postValue(searchingList)
    }

    fun getProductsByCategory(id:Int):LiveData<List<Products>> = database.getProductByCategory(id)



    fun insertProduct(
        name: String,
        des: String,
        price: Int,
        about: String,
        category: Int,
        image: Int
    ) = viewModelScope.launch(Dispatchers.IO) {
        database.insertProduct(Products(0, name, des, price, about, category, image))
    }
}