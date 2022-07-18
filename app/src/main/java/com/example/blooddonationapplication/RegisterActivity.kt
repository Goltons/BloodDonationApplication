package com.example.blooddonationapplication

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.blooddonationapplication.DataAccess.UserService
import com.example.blooddonationapplication.data.*
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {
    private lateinit var firstName: EditText
    private lateinit var lastName: EditText
    private lateinit var password: EditText
    private lateinit var identificationNumber: EditText
    private lateinit var bloodGroup: EditText
    private lateinit var city: EditText
    private lateinit var district: EditText
    private lateinit var quarter: EditText
    private lateinit var street: EditText
    private lateinit var buildingNumber: EditText
    private lateinit var apartmentNumber: EditText
    private lateinit var phoneNumber: EditText
    private lateinit var birthDate: EditText
    private lateinit var email: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        firstName=findViewById(R.id.firstName)
        lastName=findViewById(R.id.lastName)
        password=findViewById(R.id.password)
        identificationNumber=findViewById(R.id.identificationNumber)
        bloodGroup=findViewById(R.id.bloodGroup)
        city=findViewById(R.id.city)
        district=findViewById(R.id.district)
        quarter=findViewById(R.id.quarter)
        street=findViewById(R.id.street)
        buildingNumber=findViewById(R.id.buildingNumber)
        apartmentNumber=findViewById(R.id.apartmentNumber)
        phoneNumber=findViewById(R.id.phoneNumber)
        birthDate=findViewById(R.id.birthDate)
        email=findViewById(R.id.email)

    }
    val db= FirebaseFirestore.getInstance()
    val userDocRef: CollectionReference =db.collection("users")
    @Throws(IllegalArgumentException::class)
     fun btnSave(view: View){
        val userService= UserService()
        val user=User()
        val address=Address()
        val communication=Communication()
        user.userFirstName=firstName.text.trim().toString()
        user.userLastName =lastName.text.trim().toString()
        user.userPassword=password.text.trim().toString()
        user.identificationNumber=identificationNumber.text.toString().toLongOrNull()
        user.bloodGroup=bloodGroup.text.trim().toString()
        address.addressId=0
        user.userId=0
        communication.communicationId=0
        address.city= city.text.trim().toString()
        address.district=district.text.trim().toString()
        address.quarter=quarter.text.trim().toString()
        address.street=street.text.trim().toString()
        address.buildingNumber=buildingNumber.text.toString().toIntOrNull()
        address.apartmentNumber=apartmentNumber.text.toString().toIntOrNull()
        communication.phoneNumber= phoneNumber.text.toString().toLongOrNull()
        user.birthDate=birthDate.text.toString()
        communication.email=email.text.trim().toString()
        communication.userIdNo=identificationNumber.text.toString().toLongOrNull()
        address.userIdNp=identificationNumber.text.toString().toLongOrNull()

        user.communication=communication
        user.address=address
        val diseaseInformation=ArrayList<DiseaseInformation>()
        val bloodDonations= ArrayList<BloodDonations>()
        user.diseaseInformation=diseaseInformation
        user.bloodDonations=bloodDonations

/*
        val address=Address(0,city,district,quarter,street,buildingNumber,apartmentNumber
            ,IdentificationNumber)
        val communication=Communication(0,email,phoneNumber,IdentificationNumber)
        val bloodDonations= ArrayList<BloodDonations>()
        val diseaseInformation=ArrayList<DiseaseInformation>()
        val user=User(0,firstName,lastName,password, IdentificationNumber,
            bloodGroup,birthDate,address, communication, diseaseInformation, bloodDonations)*/
//validate methodu çağıralacak
if(userService.validate(user)){
        userDocRef
            .whereEqualTo("identificationNumber", user.identificationNumber)
            .get().addOnSuccessListener { task->
                if (task.isEmpty){
                    userDocRef.add(user)
                    val intent= Intent(this,LoginActivity::class.java)
                    startActivity(intent)
                }
                else
                    Toast.makeText(this,"eksik veya hatalı giriş yaptınız.", Toast.LENGTH_LONG).show()
                Log.d(ContentValues.TAG,"döküman var mı ?")
            }.addOnFailureListener{
                throw it
            }}
        else
            Toast.makeText(this,"eksik veya hatalı .", Toast.LENGTH_LONG).show()
        /*userService.save(user)
        if(userService.sonuc=="success"){
            Log.d("activity","${userService.sonuc}")
                 val intent=Intent(this,LoginActivity::class.java)
                 startActivity(intent)
        }
        else
             Toast.makeText(this,"eksik veya hatalı giriş yaptınız.",Toast.LENGTH_LONG).show()*/




    }

}