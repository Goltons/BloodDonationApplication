package com.example.blooddonationapplication.data

//@Entity
data class BloodDonations(
    //@PrimaryKey(autoGenerate = true)
    val donationId:Int,
    var donorIdNo:Long,
    var receiverIdNo:Long,
    var bloodGroup:String,
    var count:Int,
    var userIdNo: Long
    ):BaseDataClass(){
}