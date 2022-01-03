package com.example.blooddonationapplication

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.blooddonationapplication.data.BloodDonationApplyment
import com.google.firebase.firestore.FirebaseFirestore

class AddApplyActivity : AppCompatActivity() {
    private lateinit var editText_hospitalName:EditText
    private lateinit var numberPicker_bloodGroup: NumberPicker
    private lateinit var editText_description: EditText
    private lateinit var editText_patientIdNo: EditText
    private lateinit var editText_count: EditText
    private lateinit var editText_location: EditText
    private lateinit var editText_patientName: EditText
    private var valueBloodGroup:String=""
    private var bloodGroupArray:List<String>?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_apply)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_add_24)
        setTitle("Başvuru")
        editText_hospitalName=findViewById(R.id.editText_hospitalName)
        editText_description=findViewById(R.id.editText_description)
        editText_count=findViewById(R.id.editText_count)
        editText_patientIdNo=findViewById(R.id.editText_PatientIdNo)
        editText_location=findViewById(R.id.editText_location)
        editText_patientName=findViewById(R.id.editText_patientName)
        numberPicker_bloodGroup=findViewById(R.id.numberPicker_bloodGroup)
        numberPicker_bloodGroup.minValue=0
        numberPicker_bloodGroup.maxValue=7
        numberPicker_bloodGroup.setDisplayedValues(listOf("0RH+", "0RH-", "ABRH+","ABRH-","ARH+","ARH-","BRH+","BRH-").toTypedArray())

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater:MenuInflater=getMenuInflater()
        menuInflater.inflate(R.menu.add_apply_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.add_apply-> {
                addApply()
                true
            }
            else -> false


        }
        return super.onOptionsItemSelected(item)
    }

     fun addApply() {
        val bloodDonationApplyment=BloodDonationApplyment()
        bloodDonationApplyment.hospitalName=editText_hospitalName.text.trim().toString()
        bloodDonationApplyment.description=editText_description.text.trim().toString()
        bloodDonationApplyment.countOfNeeds=editText_count.text.toString().toInt()
        bloodDonationApplyment.complete=false
         bloodDonationApplyment.patientName=editText_patientName.toString()
        bloodDonationApplyment.location=editText_location.text.trim().toString()
        bloodDonationApplyment.patientIdNo=editText_patientIdNo.text.toString().toLong()
         when(numberPicker_bloodGroup.value){
             0->bloodDonationApplyment.bloodGroup="0RH+"
             1->bloodDonationApplyment.bloodGroup="0RH-"
             2->bloodDonationApplyment.bloodGroup="ABRH+"
             3->bloodDonationApplyment.bloodGroup="ABRH-"
             4->bloodDonationApplyment.bloodGroup="ARH+"
             5->bloodDonationApplyment.bloodGroup="ARH-"
             6->bloodDonationApplyment.bloodGroup="BRH+"
             7->bloodDonationApplyment.bloodGroup="BRH-"
             else
                 -> false
         }


        if(bloodDonationApplyment.hospitalName==null||bloodDonationApplyment.description==null
            ||bloodDonationApplyment.countOfNeeds==null||bloodDonationApplyment.location==null
            ||bloodDonationApplyment.patientIdNo==null||bloodDonationApplyment.patientIdNo==null
            || bloodDonationApplyment.patientIdNo!! %2!=0L||bloodDonationApplyment.patientIdNo.toString().startsWith("0")
            ||bloodDonationApplyment.patientIdNo.toString().length!=11){
            Toast.makeText(this, "Girilen değerler hatalı", Toast.LENGTH_SHORT).show();
            return;
        }
        val applyRef=FirebaseFirestore.getInstance().collection("apply")
        applyRef.add(bloodDonationApplyment)
        Toast.makeText(this, "Başvuru eklendi", Toast.LENGTH_SHORT).show();
        finish();

    }
}