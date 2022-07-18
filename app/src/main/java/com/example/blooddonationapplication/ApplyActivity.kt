package com.example.blooddonationapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
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
    private lateinit var hospitalName:TextView
    private lateinit var patientName:TextView
    private lateinit var bloodGroup:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apply)
        hospitalName=findViewById(R.id.hospitalName)
        patientName=findViewById(R.id.patientName)
        bloodGroup=findViewById(R.id.bloodGroupApply)
        val id = intent?.getStringExtra("id")
        if (id != null) {
            collectionRef.document(id).get().addOnSuccessListener { task->
                val apply=task.toObject(BloodDonationApplyment::class.java);
                if (apply != null) {
                    onStart(apply)
                }
            }
        }
    }
      private fun onStart(apply:BloodDonationApplyment) {
        hospitalName.text=apply.hospitalName.toString()
        patientName.text=apply.patientName.toString()
        bloodGroup.text=apply.bloodGroup.toString()
    }
    fun btnApplyDonor(view: View){
        val id = intent?.getStringExtra("id")
        if (id != null) {
            collectionRef.document(id).get()
                .addOnSuccessListener {  task->
                    val apply=task.toObject(BloodDonationApplyment::class.java)
                        apply?.countOfNeeds = apply?.countOfNeeds?.minus(1)
                        collectionRef.document(id).update("countOfNeeds",apply?.countOfNeeds)
                    val sp=getSharedPreferences("sharedPrefs", MODE_PRIVATE)
                    val donor= Donor(sp.getLong("idNo",0),id,true,apply?.patientName.toString()
                        ,apply?.hospitalName.toString(),sp.getString("bloodGroup","").toString())
                    var a=false
                    if(apply?.countOfNeeds==0){
                            println("if1e girdi")
                            collectionRef.document(id).update("complete",true)
                        Thread.sleep(150)
                           db.collection("donors")
                               .whereEqualTo("donorIdNo",sp.getLong("idNo",0))
                               .whereEqualTo("applyId",id)
                               .whereEqualTo("complete",false)
                               .get().addOnSuccessListener { task->
                                   if (task.size()==0){
                                       println("else1 e girdi")
                                       collectionRef.document(id).collection("donors").add(donor)
                                       db.collection("donors").add(donor);
                                       val intent=Intent(this,ApplymentHistoryActivity::class.java )
                                       startActivity(intent)

                                   }
                                   else
                                   {
                                       for (docs in task){
                                           db.collection("donors").document(docs.id)
                                               .update("complete",true);}
                                       val intent=Intent(this,ApplymentHistoryActivity::class.java )
                                       startActivity(intent)
                                   }

                               }
                    }
                    else{
                            println("if2e girdi")
                            db.collection("donors")
                                .whereEqualTo("donorIdNo",sp.getLong("idNo",0))
                                .whereEqualTo("applyId",id)
                                .whereEqualTo("complete",false)
                                .get().addOnSuccessListener { task->
                                    println(task.size())
                                    if(task.size()==0){
                                        println("ife girdi")
                                        collectionRef.document(id).collection("donors").add(donor)
                                        db.collection("donors").add(donor);
                                        db.waitForPendingWrites()
                                        val intent=Intent(this,ApplymentHistoryActivity::class.java )
                                        startActivity(intent)
                                    }
                                    else{
                                    for (docs in task){
                                        db.collection("donors").document(docs.id)
                                            .update("complete",true);}
                                        val intent=Intent(this,ApplymentHistoryActivity::class.java )
                                        startActivity(intent)
                                    }}
                                }
                    }
                }
        }
}