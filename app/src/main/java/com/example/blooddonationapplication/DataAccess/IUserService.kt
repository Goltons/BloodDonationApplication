package com.example.blooddonationapplication.DataAccess

import com.example.blooddonationapplication.data.User


interface IUserService {
    fun save(user:User):Unit
    fun validate(user: User):Boolean
    fun login(idNo:Long,password:String):Boolean
    fun logOut():Unit
    fun getUser(idNo:Long):String
}