package com.example.dfhackthon.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dfhackthon.R
import com.example.dfhackthon.databinding.ItemSlotTimeLayoutBinding
import com.example.dfhackthon.model.availableSeat.Availability

class AvailableSeatAdapter(
    private val context: Context,
    private var list: ArrayList<Availability>,
    val callback: SelectedIdCallBack,
) : RecyclerView.Adapter<AvailableSeatAdapter.CalendarViewHolder>() {

    private var selectedPosition: Int = -1

    inner class CalendarViewHolder(val binding: ItemSlotTimeLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val binding = ItemSlotTimeLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CalendarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val slot = list[position]

        holder.binding.tvTime.text = slot.workspace_name

        if (selectedPosition == position) {
            holder.binding.clMain.setBackgroundResource(R.drawable.avail_select)
            holder.binding.tvTime.setTextColor(context.getColor(R.color.white))
            holder.itemView.isEnabled = true
        } else if (slot.workspace_active) {
            holder.binding.clMain.setBackgroundResource(R.drawable.avail_false)
            holder.itemView.isEnabled = true
        } else {
            holder.binding.clMain.setBackgroundResource(R.drawable.avail_true)
            holder.binding.tvTime.setTextColor(context.getColor(R.color.white))
            holder.itemView.isEnabled = false
        }

        holder.itemView.setOnClickListener {
            if (slot.workspace_active) {
                val previousPosition = selectedPosition
                selectedPosition = position
                notifyItemChanged(previousPosition)
                notifyItemChanged(selectedPosition)
                callback.onClickDate(slot.workspace_id, slot.workspace_name)
            }
        }
    }

    override fun getItemCount(): Int = list.size

    interface SelectedIdCallBack {
        fun onClickDate(workSpaceId : Int, workSpaceName : String)
    }
}