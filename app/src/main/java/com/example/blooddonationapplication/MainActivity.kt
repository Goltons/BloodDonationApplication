package com.example.blooddonationapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    fun btnRegister(view: View){
        val intent= Intent(this,RegisterActivity::class.java)
        startActivity(intent)
    }
    fun btnLogin(view: View){
        val intent= Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }
}