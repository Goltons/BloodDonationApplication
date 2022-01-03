package com.example.blooddonationapplication.data

data class Donor(
    var donorIdNo:Long=0,
    var count:Int=0,
    var patientName:String="",
    var hospitalName:String="",
    var bloodGroup:String=""
)
    :BaseDataClass(){
}