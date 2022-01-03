package com.example.blooddonationapplication.data

//@Entity
data class Address(
    //@PrimaryKey(autoGenerate = true)
    var addressId:Int?,
    var city:String?,
    var district:String?,
    var quarter:String?,
    var street:String?,
    var buildingNumber:Int?,
    var apartmentNumber:Int?,
    var userIdNp: Long=0L
):BaseDataClass()