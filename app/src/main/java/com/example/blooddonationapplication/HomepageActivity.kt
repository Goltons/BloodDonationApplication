package com.example.blooddonationapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HomepageActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        val sp =getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        if (!sp.contains("idNo")&&!sp.contains("pw"))
        {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

    }
    fun getApplys(view: View){
        val intent = Intent(this,ApplymentsActivity::class.java)
        startActivity(intent)
    }
    fun addApplys(view: View){
        val intent = Intent(this,AddApplyActivity::class.java)
        startActivity(intent)
    }
    fun applyHistory(view: View){
        val intent = Intent(this,ApplymentHistoryActivity::class.java)
        startActivity(intent)
    }


    fun btnLogout(view: View){
        val sp =getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        Toast.makeText(this,"Çıkış yapıldı."
            , Toast.LENGTH_SHORT).show()
        sp.edit().clear().apply()
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }
}