package com.example.hardwarestore.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.hardwarestore.model.DBase
import com.example.hardwarestore.model.Users
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrationViewModel(app: Application) : AndroidViewModel(app) {
    private  val dataBase = DBase.getInstance(app).usersDao()





    fun userById(id:Int):LiveData<Users> = dataBase.getIdUser(id)

    fun getUsers(number:String,passwordUser: String) = dataBase.getUser(number, passwordUser)




    fun insertNewUser(firstName:String,lastName:String,nickName:String,passwordUser:String,numberTelefonUser:String)
    = viewModelScope.launch(Dispatchers.IO){
        dataBase.insertUser(Users(firstName,lastName,nickName,passwordUser,numberTelefonUser ))
    }

    fun update(imageUri: String, userId: Int) = viewModelScope.launch(Dispatchers.IO) {
        dataBase.update(imageUri, userId)
    }

    fun getImage(userId: Int) = dataBase.getImage(userId)


    fun deleteUser(firstName:String,lastName:String,nickName:String,passwordUser:String,numberTelefonUser:String)
            = viewModelScope.launch(Dispatchers.IO){
        dataBase.delete(Users(firstName,lastName,nickName,passwordUser,numberTelefonUser))
    }



}