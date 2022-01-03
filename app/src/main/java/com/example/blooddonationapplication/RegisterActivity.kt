package com.example.blooddonationapplication

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.blooddonationapplication.DataAccess.UserService
import com.example.blooddonationapplication.data.*
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

    }
    val db= FirebaseFirestore.getInstance()
    val userDocRef: CollectionReference =db.collection("users")
    @Throws(IllegalArgumentException::class)
     fun btnSave(view: View){
        val userService= UserService()
        val firstName=firstName.text.trim().toString()
        val lastName =lastName.text.trim().toString()
        val password=password.text.trim().toString()
        val IdentificationNumber=identificationNumber.text.toString().toLong()
        val bloodGroup=bloodGroup.text.trim().toString()
        val city= city.text.trim().toString()
        val district=district.text.trim().toString()
        val quarter=quarter.text.trim().toString()
        val street=street.text.trim().toString()
        val buildingNumber=buildingNumber.text.toString().toInt()
        val apartmentNumber=apartmentNumber.text.toString().toInt()
        val phoneNumber=phoneNumber.text.toString().toLong()
        val birthDate=birthDate.text.toString()
        val email=email.text.trim().toString()

        val address=Address(0,city,district,quarter,street,buildingNumber,apartmentNumber
            ,IdentificationNumber)
        val communication=Communication(0,email,phoneNumber,IdentificationNumber)
        val bloodDonations= ArrayList<BloodDonations>()
        val diseaseInformation=ArrayList<DiseaseInformation>()
        val user=User(0,firstName,lastName,password, IdentificationNumber,
            bloodGroup,birthDate,address, communication, diseaseInformation, bloodDonations)
//validate methodu çağıralacak
if(userService.validate(user)){
        userDocRef
            .whereEqualTo("identificationNumber", IdentificationNumber)
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