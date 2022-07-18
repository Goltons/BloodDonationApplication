package com.example.blooddonationapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.blooddonationapplication.DataAccess.UserService
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessaging
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

        if(identificationNo.text.toString().isEmpty() || userPassword.text.isEmpty()){
            Toast.makeText(this,"Yanlış veya hatalı giriş yaptınız tekrar deneyiniz"
                , Toast.LENGTH_LONG).show()
        }
        else{
            val idNo=identificationNo.text.toString().toLong()
            val pw=userPassword.text.toString()
        userDocRef.whereEqualTo("identificationNumber",idNo).whereEqualTo("userPassword",pw)
            .get().addOnSuccessListener { task->
                if(!task.isEmpty){

                    edior.apply{
                        putLong("idNo",idNo)
                        putString("pw",pw)
                        putString("bloodGroup",task.documents[0].get("bloodGroup").toString())
                        putString("docId",task.documents[0].id)
                    }.apply()
                    Toast.makeText(this,task.documents[0].id,Toast.LENGTH_LONG).show()
                    saveToken()

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
    } }
    @SuppressLint("CommitPrefEdits")
    fun saveToken(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }
            val sp =getSharedPreferences("sharedPrefs", MODE_PRIVATE)
            val editor=sp.edit()
            val token = task.result
            val docId=sp.getString("docId","")
            val db= FirebaseFirestore.getInstance()
            println("$token $docId")
            if(docId!=null || docId!="")
                db.collection("users").document(docId!!).update("deviceToken",token)
        })
        db.waitForPendingWrites()
    }
    fun btnRegister(view: View){
        val intent= Intent(this,RegisterActivity::class.java)
        startActivity(intent)
    }
}