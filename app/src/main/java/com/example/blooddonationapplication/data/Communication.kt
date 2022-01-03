package com.example.blooddonationapplication.data

//@Entity
data class Communication(
  //  @PrimaryKey(autoGenerate = true)
  var communicationId:Int,
  var email:String,
  var phoneNumber:Long,
  var userIdNo: Long
) :BaseDataClass() {
  constructor():this(0,"",0,0)
}