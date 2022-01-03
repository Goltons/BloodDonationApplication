package com.example.blooddonationapplication.data

import javax.annotation.Nullable

@Nullable
//@Entity(tableName = "users")
data class User (
    //@PrimaryKey(autoGenerate = true)
    var userId:Int?=null,
    var userFirstName:String?,
    var userLastName:String?,
    var userPassword:String?,
    var identificationNumber:Long?,
    var bloodGroup:String?,
    var birthDate: String?,
    var address:Address?,
    var communication:Communication?,
    var diseaseInformation:ArrayList<DiseaseInformation>,
    var bloodDonations:ArrayList<BloodDonations>
    ):BaseDataClass()

