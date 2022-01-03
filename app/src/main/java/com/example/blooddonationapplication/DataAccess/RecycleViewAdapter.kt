package com.example.blooddonationapplication.DataAccess

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blooddonationapplication.R
import com.example.blooddonationapplication.data.BloodDonationApplyment

class RecycleViewAdapter(val itemList:ArrayList<BloodDonationApplyment>):RecyclerView.Adapter<itemListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): itemListViewHolder {
        return  itemListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.applymentlist,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: itemListViewHolder, position: Int) {
        var item=itemList.get(position)
      // holder.bindItems(itemList[position])
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}