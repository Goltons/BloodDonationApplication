package com.example.blooddonationapplication.DataAccess

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.blooddonationapplication.R
import com.example.blooddonationapplication.data.BloodDonationApplyment
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.DocumentSnapshot




class ApplyAdapter (options: FirestoreRecyclerOptions<BloodDonationApplyment>,bloodGroup:String) :
    FirestoreRecyclerAdapter<BloodDonationApplyment, ApplyAdapter.BloodDonationAppleymentHolder>(
        options
    ) {
       var  listener: OnItemClickListener? = null
    var bloodGroupStr=bloodGroup
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ApplyAdapter.BloodDonationAppleymentHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.applymentlist,parent,false)
        return BloodDonationAppleymentHolder(view)
    }
   inner class BloodDonationAppleymentHolder:RecyclerView.ViewHolder {
        var textViewHospitalName: TextView? =null
        var textViewDesc:TextView?=null
        var textViewBloodGroup:TextView?=null
        var textViewCount:TextView?=null
        var textViewLocation:TextView?=null
       var relativeLayout:RelativeLayout?=null
        constructor(itemView:View) : super(itemView) {
            textViewHospitalName = itemView.findViewById(R.id.text_view_hospitalName)
             textViewDesc = itemView.findViewById(R.id.text_view_description)
            textViewBloodGroup = itemView.findViewById(R.id.text_view_bloodGroup)
            textViewCount = itemView.findViewById(R.id.textView_count)
            textViewLocation = itemView.findViewById(R.id.text_view_location)
            relativeLayout=itemView.findViewById(R.id.relative)
            itemView.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION &&  listener != null) {
                    listener!!.onItemClick(getSnapshots().getSnapshot(position), position)
                }
            }
        }
    }
    fun returnListener(): OnItemClickListener? {
        return listener
    }
    interface OnItemClickListener {
        fun onItemClick(documentSnapshot: DocumentSnapshot?, position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.listener = listener
    }
    override fun onBindViewHolder(
        holder: ApplyAdapter.BloodDonationAppleymentHolder,
        position: Int,
        model: BloodDonationApplyment,
    ) {
        holder.relativeLayout?.visibility=View.GONE
        when(bloodGroupStr){
            "0RH-"->{
                holder.textViewHospitalName?.text = model.hospitalName
                holder.textViewDesc?.text = model.description
                holder.textViewBloodGroup?.text=model.bloodGroup
                holder.textViewCount?.text=model.countOfNeeds.toString()
                holder.textViewLocation?.text=model.location
                if(bloodGroupStr=="0RH-")
                     holder.relativeLayout?.visibility =View.VISIBLE
                else
                    holder.relativeLayout?.visibility =View.GONE
            }
            "0RH+"->{
                if (model.bloodGroup=="0RH+"){
                    holder.textViewHospitalName?.text = model.hospitalName
                    holder.textViewDesc?.text = model.description
                    holder.textViewBloodGroup?.text=model.bloodGroup
                    holder.textViewCount?.text=model.countOfNeeds.toString()
                    holder.textViewLocation?.text=model.location
                    holder.relativeLayout?.visibility =View.VISIBLE

                }
            }
            "ABRH+"->{
                if (model.bloodGroup=="ABRH+"){
                    holder.textViewHospitalName?.text = model.hospitalName
                    holder.textViewDesc?.text = model.description
                    holder.textViewBloodGroup?.text=model.bloodGroup
                    holder.textViewCount?.text=model.countOfNeeds.toString()
                    holder.textViewLocation?.text=model.location
                    holder.relativeLayout?.visibility =View.VISIBLE
                }
            }
            "ABRH-"->{  if (model.bloodGroup==="ABRH-"){
                holder.textViewHospitalName?.text = model.hospitalName
                holder.textViewDesc?.text = model.description
                holder.textViewBloodGroup?.text=model.bloodGroup
                holder.textViewCount?.text=model.countOfNeeds.toString()
                holder.textViewLocation?.text=model.location
                holder.relativeLayout?.visibility =View.VISIBLE

            }}
            "ARH+"->{
                if (model.bloodGroup=="ARH+"){
                holder.textViewHospitalName?.text = model.hospitalName
                holder.textViewDesc?.text = model.description
                holder.textViewBloodGroup?.text=model.bloodGroup
                holder.textViewCount?.text=model.countOfNeeds.toString()
                holder.textViewLocation?.text=model.location
                holder.relativeLayout?.visibility =View.VISIBLE

            }}
            "ARH-"->{  if (model.bloodGroup=="ARH-"){
                holder.textViewHospitalName?.text = model.hospitalName
                holder.textViewDesc?.text = model.description
                holder.textViewBloodGroup?.text=model.bloodGroup
                holder.textViewCount?.text=model.countOfNeeds.toString()
                holder.textViewLocation?.text=model.location
                holder.relativeLayout?.visibility =View.VISIBLE
            }}
            "BRH+"->{  if (model.bloodGroup=="BRH+"){
                holder.textViewHospitalName?.text = model.hospitalName
                holder.textViewDesc?.text = model.description
                holder.textViewBloodGroup?.text=model.bloodGroup
                holder.textViewCount?.text=model.countOfNeeds.toString()
                holder.textViewLocation?.text=model.location
                holder.relativeLayout?.visibility =View.VISIBLE
            }}
            "BRH-"->{  if (model.bloodGroup=="BRH-"){
                holder.textViewHospitalName?.text = model.hospitalName
                holder.textViewDesc?.text = model.description
                holder.textViewBloodGroup?.text=model.bloodGroup
                holder.textViewCount?.text=model.countOfNeeds.toString()
                holder.textViewLocation?.text=model.location
                holder.relativeLayout?.visibility =View.VISIBLE
            }}

           }        /*
  0rh--> return all
  0rh+->ends with +
  abrh+->abrh+
  abrh-->ends with-
  arh+-> starts with 0   and A
  arh-->0rh-,arh-
  brh+->starts with 0 and B
  brh-->0rh- brh-
   */
      /*  holder.textViewHospitalName?.text = model.hospitalName
        holder.textViewDesc?.text = model.description
        holder.textViewBloodGroup?.text=model.bloodGroup
        holder.textViewCount?.text=model.countOfNeeds.toString()
        holder.textViewLocation?.text=model.location*/
    }
}
