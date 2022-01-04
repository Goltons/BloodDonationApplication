package com.example.blooddonationapplication.data

//@Entity
data class Communication(
  //  @PrimaryKey(autoGenerate = true)
  var communicationId:Int?=null,
  var email:String?=null,
  var phoneNumber:Long?=null,
  var userIdNo: Long?=null
) :BaseDataClass() {
}