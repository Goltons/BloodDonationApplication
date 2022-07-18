package com.example.blooddonationapplication.data

data class Donor(
    var donorIdNo:Long=0,
    var applyId:String?="",
    var complete:Boolean=false,
    var patientName:String="",
    var hospitalName:String="",
    var bloodGroup:String=""
)
    :BaseDataClass(){
}