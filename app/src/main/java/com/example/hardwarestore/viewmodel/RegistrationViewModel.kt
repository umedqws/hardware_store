package com.example.hardwarestore.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hardwarestore.model.DBase
import com.example.hardwarestore.model.Users
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RegistrationViewModel(app: Application) : AndroidViewModel(app) {
    private  val dataBase = DBase.getInstance(app).usersDao()





    fun getAllUser() = dataBase.getAllUser()

    fun getUsers(number:String,passwordUser: String) = dataBase.getUser(number, passwordUser)




    fun insertNewUser(firstName:String,lastName:String,nickName:String,passwordUser:String,numberTelefonUser:String)
    = viewModelScope.launch(Dispatchers.IO){
        dataBase.insertUser(Users(firstName,lastName,nickName,passwordUser,numberTelefonUser ))
    }

    fun deleteUser(firstName:String,lastName:String,nickName:String,passwordUser:String,numberTelefonUser:String,id:Int)
            = viewModelScope.launch(Dispatchers.IO){
        dataBase.delete(Users(firstName,lastName,nickName,passwordUser,numberTelefonUser,id))
    }



}