package com.example.hardwarestore.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.hardwarestore.model.Categories
import com.example.hardwarestore.model.DBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel(app:Application):AndroidViewModel(app) {
    val database=DBase.getInstance(app).categoryDao()

    fun listCategory():LiveData<List<Categories>> = database.getCategory()

    fun insertCategory(name:String) = viewModelScope.launch(Dispatchers.IO) {
        database.insertCategory(Categories(name))
    }
}