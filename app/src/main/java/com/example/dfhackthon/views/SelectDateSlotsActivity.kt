package com.example.dfhackthon.views

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dfhackthon.R
import com.example.dfhackthon.adapter.CalendarAdapter
import com.example.dfhackthon.adapter.SlotTimeAdapter
import com.example.dfhackthon.databinding.ActivitySelecteDateSlotsBinding
import com.example.dfhackthon.model.CalendarDate
import com.example.dfhackthon.model.slots.Slot
import com.example.dfhackthon.network.AuthRepository
import com.example.dfhackthon.utils.Status
import com.example.dfhackthon.utils.generateCalendarDays
import com.example.dfhackthon.viewModel.AuthViewModelFactory
import com.example.dfhackthon.viewModel.RegisterViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class SelectDateSlotsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySelecteDateSlotsBinding
    private lateinit var calendarAdapter: CalendarAdapter
    private lateinit var slotTimeAdapter: SlotTimeAdapter
    private var list = ArrayList<Slot>()
    private var selectType = ""
    private var slotID = 0
    private var slotSelectTime = ""
    private var cDate: String? = null

    private val registerViewModel by viewModels<RegisterViewModel> {
        AuthViewModelFactory(Application(), AuthRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySelecteDateSlotsBinding.inflate(layoutInflater)

        selectType = intent.getStringExtra("selectType").toString()

        observers()
        registerViewModel.slotsApi(cDate.toString())
        calendarAdapter = CalendarAdapter(generateCalendarDays(), selectedDated)
        binding.rvCalender.adapter = calendarAdapter

        binding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnNext.setOnClickListener {
            if (slotID == 0) {
                Toast.makeText(this, "Please select a slot time.", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, AvailableSeatActivity::class.java)
                intent.putExtra("selectType", selectType)
                intent.putExtra("slotId", slotID)
                intent.putExtra("slotTime", slotSelectTime)
                intent.putExtra("date", cDate)
                startActivity(intent)
            }
        }

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private var slotIdByClick = object : SlotTimeAdapter.SelectedIdCallBack {
        override fun onClickDate(slotId: Int, slotTime: String) {
            slotID = slotId
            slotSelectTime = slotTime
        }
    }

    private var selectedDated = object : CalendarAdapter.CalendarDateCallback {
        override fun onClickDate(date: CalendarDate) {
            try {
                val calendar = Calendar.getInstance()
                val monthFormat = SimpleDateFormat("MMM", Locale.ENGLISH)
                calendar.apply {
                    set(Calendar.YEAR, get(Calendar.YEAR))
                    set(Calendar.MONTH, monthFormat.parse(date.month)?.month ?: 0)
                    set(Calendar.DAY_OF_MONTH, date.date.toInt())
                }
                val formattedDate = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).format(calendar.time)
                Log.d("TAG", "Formatted Date: $formattedDate")
                cDate = formattedDate
                registerViewModel.slotsApi(cDate!!)
            } catch (e: Exception) {
                Log.e("TAG", "Error formatting date: ${e.message}")
            }
        }
    }

    private fun observers() {
        registerViewModel.slotsResponse.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progress.visibility = View.GONE
                    list.clear()
                    list.addAll(it.data?.slots!!)
                    slotTimeAdapter = SlotTimeAdapter(this, list, slotIdByClick)
                    binding.rvSlots.adapter = slotTimeAdapter
                }

                Status.LOADING -> {
                    binding.progress.visibility = View.VISIBLE
                }

                Status.ERROR -> {
                    binding.progress.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}