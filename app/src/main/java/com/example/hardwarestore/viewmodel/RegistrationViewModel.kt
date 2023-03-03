package com.example.hardwarestore.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hardwarestore.model.DBase
import com.example.hardwarestore.model.Users
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RegistrationViewModel(val app: Application) : AndroidViewModel(app) {
    private  val dataBase = DBase.getInstace(app).usersDao()

    val getUser= MutableLiveData<Users>()

    fun getId(number:String) = viewModelScope.launch(Dispatchers.IO){
        dataBase.getIdUser(number)
    }

    fun getUser(id: Int){
        dataBase.getUser(id)
    }

    fun insertNewUser(firstName:String,lastName:String,nickName:String,passwordUser:String,numberTelefonUser:String)
    = viewModelScope.launch(Dispatchers.IO){
        dataBase.insertUser(Users(firstName,lastName,nickName,passwordUser,numberTelefonUser ))
    }



}