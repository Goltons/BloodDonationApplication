package com.example.blooddonationapplication.data

data class BloodDonationApplyment(
    var hospitalName:String?=null,
    var patientIdNo:Long?=null,
    var patientName:String?=null,
    var countOfNeeds:Int?=null,
    var description:String?=null,
    var bloodGroup: String?=null,
    var complete:Boolean?=null,
   // var donorsMap:ArrayList<Donor>? = null,
   //val listOfDonors:HashMap<Int,Int>,
    var location:String?=null
):BaseDataClass() {


}