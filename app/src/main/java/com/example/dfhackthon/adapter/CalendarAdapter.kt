package com.example.dfhackthon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dfhackthon.R
import com.example.dfhackthon.databinding.DayDateLayoutBinding
import com.example.dfhackthon.model.CalendarDate

class CalendarAdapter(
    private val dates: List<CalendarDate>,
    val callback: CalendarDateCallback,
) : RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    private var selectedPosition = 0

    inner class CalendarViewHolder(val binding: DayDateLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val binding = DayDateLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CalendarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val date = dates[position]
        holder.binding.apply {
            tvDay.text = date.dayOfWeek
            tvDate.text = date.date
            tvMonth.text = date.month

            if (selectedPosition == position) {
                callback.onClickDate(dates[position])
                clDate.setBackgroundResource(R.drawable.date_bg)
                tvDay.setTextColor(root.context.getColor(R.color.white))
                tvDate.setTextColor(root.context.getColor(R.color.white))
                tvMonth.setTextColor(root.context.getColor(R.color.white))
            } else {
                clDate.setBackgroundColor(root.context.getColor(R.color.white))
                tvDay.setTextColor(root.context.getColor(R.color.black))
                tvDate.setTextColor(root.context.getColor(R.color.black))
                tvMonth.setTextColor(root.context.getColor(R.color.black))
            }

            root.setOnClickListener {
                val previousPosition = selectedPosition
                selectedPosition = position
                notifyItemChanged(previousPosition)
                notifyItemChanged(position)

            }
        }
    }

    override fun getItemCount(): Int = dates.size

    interface CalendarDateCallback{
        fun onClickDate(date :CalendarDate)
    }
}
