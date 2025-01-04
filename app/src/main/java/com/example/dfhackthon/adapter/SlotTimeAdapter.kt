package com.example.dfhackthon.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dfhackthon.R
import com.example.dfhackthon.databinding.ItemSlotTimeLayoutBinding
import com.example.dfhackthon.model.slots.Slot

class SlotTimeAdapter(
    private val context: Context,
    private var list: ArrayList<Slot>,
    val callback: SelectedIdCallBack,
) : RecyclerView.Adapter<SlotTimeAdapter.CalendarViewHolder>() {

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

        holder.binding.tvTime.text = slot.slot_name

        if (selectedPosition == position) {
            holder.binding.clMain.setBackgroundResource(R.drawable.selected_slot_bg)
            holder.binding.tvTime.setTextColor(context.getColor(R.color.white))
            holder.itemView.isEnabled = true
        } else if (slot.slot_active) {
            holder.binding.clMain.setBackgroundResource(R.drawable.selected_true)
            holder.itemView.isEnabled = true
        } else {
            holder.binding.clMain.setBackgroundResource(R.drawable.selected_false)
            holder.binding.tvTime.setTextColor(context.getColor(R.color.white))
            holder.itemView.isEnabled = false
        }

        holder.itemView.setOnClickListener {
            if (slot.slot_active) {
                val previousPosition = selectedPosition
                selectedPosition = position
                notifyItemChanged(previousPosition)
                notifyItemChanged(selectedPosition)
                callback.onClickDate(slot.slot_id, list[position].slot_name)
            }
        }
    }

    override fun getItemCount(): Int = list.size

    interface SelectedIdCallBack {
        fun onClickDate(slotId: Int, slotTime: String)
    }
}
