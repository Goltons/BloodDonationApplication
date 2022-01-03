package com.example.blooddonationapplication.data

//@Entity(tableName = "disease_information")
data class DiseaseInformation(
   // @PrimaryKey(autoGenerate = true)
    var informationId:Int,
    var diseaseName:String,
    var userIdNo: Long
    ) :BaseDataClass(){
}