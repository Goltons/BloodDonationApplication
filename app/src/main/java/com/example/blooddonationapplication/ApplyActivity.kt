package com.example.blooddonationapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.blooddonationapplication.data.BloodDonationApplyment
import com.example.blooddonationapplication.data.Donor
import com.google.firebase.firestore.FirebaseFirestore

class ApplyActivity : AppCompatActivity() {
    private val inte=Intent()
    private val id:String=""
    private var db=FirebaseFirestore.getInstance()
    private var collectionRef=db.collection("apply")
    private lateinit var numberPicker_count: NumberPicker
    private lateinit var hospitalName:TextView
    private lateinit var patientName:TextView
    private lateinit var bloodGroup:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply)
        numberPicker_count=findViewById(R.id.count)
        hospitalName=findViewById(R.id.hospitalName)
        patientName=findViewById(R.id.patientName)
        bloodGroup=findViewById(R.id.bloodGroupApply)

        numberPicker_count.minValue=1
        numberPicker_count.maxValue=3

        val id = intent?.getStringExtra("id")
        if (id != null) {
            collectionRef.document(id).get()
                .addOnSuccessListener { task->
                    val apply=task.toObject(BloodDonationApplyment::class.java)
                    if (apply != null) {
                        if (apply.countOfNeeds!! <3){
                            numberPicker_count.maxValue=apply.countOfNeeds!!
                        }
                        onStart(apply)
                    }
                }
        }

    }

      fun onStart(apply:BloodDonationApplyment) {
        hospitalName.text=apply.hospitalName.toString()
        patientName.text=apply.patientName.toString()
          bloodGroup.text=apply.bloodGroup.toString()

    }
    fun btnApplyDonor(view: View){
        val id = intent?.getStringExtra("id")
        if (id != null) {
            collectionRef.document(id).get()
                .addOnSuccessListener { task->
                    val apply=task.toObject(BloodDonationApplyment::class.java)
                        apply?.countOfNeeds = apply?.countOfNeeds?.minus(numberPicker_count.value)
                        collectionRef.document(id).update("countOfNeeds",apply?.countOfNeeds)
                    val sp=getSharedPreferences("sharedPrefs", MODE_PRIVATE)
                    val donor= Donor(sp.getLong("idNo",0),numberPicker_count.value,apply?.patientName.toString()
                        ,apply?.hospitalName.toString(),sp.getString("bloodGroup","abc ").toString())
                    if(apply?.countOfNeeds==0){
                        collectionRef.document(id).collection("donors").add(donor)
                        collectionRef.document(id).update("isComplete",true)
                        val intent=Intent(this,ApplymentHistoryActivity::class.java )
                        startActivity(intent)
                    }
                        collectionRef.document(id).collection("donors").add(donor)
                }
                }
        }

}