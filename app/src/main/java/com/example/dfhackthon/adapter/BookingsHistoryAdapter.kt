package com.example.dfhackthon.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dfhackthon.databinding.ItemBookingHistoryLayoutBinding
import com.example.dfhackthon.model.bookingResponse.Booking

class BookingsHistoryAdapter(val context: Context, val list: ArrayList<Booking>) :
    RecyclerView.Adapter<BookingsHistoryAdapter.MyViewHolder>() {

    inner class MyViewHolder(val binding: ItemBookingHistoryLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = ItemBookingHistoryLayoutBinding.inflate(inflater, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int{
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.tvRoomID.text = list[position].workspace_id.toString()
        holder.binding.tvName.text = list[position].workspace_name
        holder.binding.tvBookedOn.text = list[position].booking_date
    }
}