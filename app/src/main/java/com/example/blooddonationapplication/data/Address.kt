package com.example.blooddonationapplication.data

//@Entity
data class Address(
    //@PrimaryKey(autoGenerate = true)
    var addressId:Int?=null,
    var city:String?=null,
    var district:String?=null,
    var quarter:String?=null,
    var street:String?=null,
    var buildingNumber:Int?=null,
    var apartmentNumber:Int?=null,
    var userIdNp: Long?=null
):BaseDataClass() {
}