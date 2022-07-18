package com.example.blooddonationapplication

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint


class HomepageActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient:FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback:LocationCallback
    private lateinit var locationResult: LocationResult
    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)
        val sp =getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        if (!sp.contains("idNo")&&!sp.contains("pw"))
        {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        fusedLocationClient=LocationServices.getFusedLocationProviderClient(this)

    }

    override fun onStart() {
        super.onStart()
        checkPermission()
    }
    private fun checkPermission(){
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),1)
            return
        }
        val task=fusedLocationClient.lastLocation
            task.addOnSuccessListener {lct->
                if(lct!=null){
                    val db= FirebaseFirestore.getInstance()
                    val sp =getSharedPreferences("sharedPrefs", MODE_PRIVATE)
                    db.collection("users")
                        .whereEqualTo("identificationNumber",sp.getLong("idNo",0))
                        .get().addOnSuccessListener {
                            if (it.size()>0){
                                val id=it.documents[0].id
                                val location=GeoPoint(lct.latitude,lct.longitude)
                                db.collection("users").document(id).update("location",location)
                            }
                        }
                    Toast.makeText(this,"${lct.latitude} ${lct.longitude}",Toast.LENGTH_LONG).show()
                }

            }
    }
    /*@SuppressLint("CommitPrefEdits")
     fun onStart1() {
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
    }*/
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
        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }
}