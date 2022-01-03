package com.example.blooddonationapplication.DataAccess

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.blooddonationapplication.R
import com.example.blooddonationapplication.data.Donor
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class ApplyHistoryAdapter(options: FirestoreRecyclerOptions<Donor>) :
    FirestoreRecyclerAdapter<Donor, ApplyHistoryAdapter.DonorHolder>(
        options
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DonorHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.applyhistorylist,parent,false)
        return DonorHolder(view)
    }

    class DonorHolder:RecyclerView.ViewHolder {

        var textViewHospitalName: TextView? =null
        var textViewDesc:TextView?=null
        var textViewBloodGroup:TextView?=null
        var textViewCount:TextView?=null
        var textViewDate:TextView?=null
        constructor(itemView:View) : super(itemView) {
            textViewHospitalName = itemView.findViewById(R.id.text_view_hospitalNameHistory)
            textViewDesc = itemView.findViewById(R.id.text_view_descriptionHistory)
            textViewBloodGroup = itemView.findViewById(R.id.text_view_bloodGroupHistory)
            textViewCount = itemView.findViewById(R.id.textView_countHistory)
            textViewDate = itemView.findViewById(R.id.text_view_dateHistory)
        }
    }

    override fun onBindViewHolder(
        holder: ApplyHistoryAdapter.DonorHolder,
        position: Int,
        model: Donor
    ) {
        holder.textViewHospitalName?.text = model.hospitalName
        holder.textViewDesc?.text = "123"
        holder.textViewBloodGroup?.text=model.bloodGroup
        holder.textViewCount?.text=model.count.toString()
        holder.textViewDate?.text=model.createdAt.toString()
    }
}