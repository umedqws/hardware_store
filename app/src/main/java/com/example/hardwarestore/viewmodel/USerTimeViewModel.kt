package com.example.hardwarestore.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hardwarestore.model.DBase
import com.example.hardwarestore.model.UserTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserTimeViewModel(app: Application) : AndroidViewModel(app) {
    private val dataBASE = DBase.getInstance(app).timeDao()

    fun insertTImeUser(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        dataBASE.insertTimeUser(UserTime(id))
    }
    private val _user = MutableLiveData<Int?>()
    val user: LiveData<Int?> = _user

    fun getUserTime(): LiveData<List<UserTime>> =  dataBASE.getUserTime()

    fun delete(userId:Int,id:Int) = viewModelScope.launch(Dispatchers.IO) {
        dataBASE.delete(UserTime(userId,id))
    }

}