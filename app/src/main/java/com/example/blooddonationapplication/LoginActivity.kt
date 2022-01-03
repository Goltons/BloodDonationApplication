package com.example.blooddonationapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.blooddonationapplication.DataAccess.UserService
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
    val db= FirebaseFirestore.getInstance()
    val userDocRef: CollectionReference =db.collection("users")
    fun btnLogin(view: View){
        val userService=UserService()
        val sp=getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        val edior=sp.edit()
        val idNo=identificationNo.text.toString().toLong()
        val pw=userPassword.text.toString()
        userDocRef.whereEqualTo("identificationNumber",idNo).whereEqualTo("userPassword",pw)
            .get().addOnSuccessListener { task->
                if(!task.isEmpty){

                    edior.apply{
                        putLong("idNo",idNo)
                        putString("pw",pw)
                        putString("bloodGroup",task.documents[0].get("bloodGroup").toString())
                    }.apply()
                    val intent= Intent(this,HomepageActivity::class.java)
                    startActivity(intent)
                }
                else
                    Toast.makeText(this,"Yanlış veya hatalı giriş yaptınız tekrar deneyiniz"
                        , Toast.LENGTH_LONG).show()
            }.addOnFailureListener{
                throw it
            }
        /*if(userService.login(idNo,pw)){
            val sp=getSharedPreferences("sharedPrefs", MODE_PRIVATE)
            val editor=sp.edit()
            editor.apply {
                putLong("idNo",idNo)
                putString("pw",pw)
                /*val user=userService.getUser(idNo)
                putString("user",user)*/
            }.apply()
            val intent=Intent(this,HomepageActivity::class.java)
            startActivity(intent)
        }
        else
            Toast.makeText(this,"Yanlış veya hatalı giriş yaptınız tekrar deneyiniz"
                , Toast.LENGTH_LONG).show()*/
    }
}