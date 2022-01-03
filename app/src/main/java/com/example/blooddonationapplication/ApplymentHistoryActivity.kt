package com.example.blooddonationapplication

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.blooddonationapplication.DataAccess.ApplyHistoryAdapter
import com.example.blooddonationapplication.data.Donor
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.FirebaseFirestore

class ApplymentHistoryActivity : AppCompatActivity() {
    private val db= FirebaseFirestore.getInstance()
    private val collectionRef=db.collectionGroup("donors")
    private var adapter:ApplyHistoryAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_applyment_history)
        //recylerview gerekli bunu listelemek için
        setRecyclerView()

    }
    private fun setRecyclerView() {
         val sp:SharedPreferences=getSharedPreferences("sharedPrefs", MODE_PRIVATE)
        val query= collectionRef.whereEqualTo("donorIdNo",sp.getLong("idNo",0))
        val options: FirestoreRecyclerOptions<Donor> = FirestoreRecyclerOptions.Builder<Donor>()
            .setQuery(query, Donor::class.java).build()
        adapter= ApplyHistoryAdapter(options)
        val recyclerView: RecyclerView =findViewById(R.id.rv_applyHistory)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.adapter=adapter
    }

    override fun onStart() {
        super.onStart()
        adapter?.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter?.stopListening()
    }
}