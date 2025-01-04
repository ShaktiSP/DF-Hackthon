package com.example.dfhackthon.views

import android.app.AlertDialog
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dfhackthon.R
import com.example.dfhackthon.adapter.AvailableSeatAdapter
import com.example.dfhackthon.databinding.ActivityAvailableSeatBinding
import com.example.dfhackthon.databinding.DialogItemBoxLayoutBinding
import com.example.dfhackthon.model.availableSeat.Availability
import com.example.dfhackthon.model.confirmation.BookingConfirmationRequest
import com.example.dfhackthon.network.AuthRepository
import com.example.dfhackthon.utils.Status
import com.example.dfhackthon.utils.formatDate
import com.example.dfhackthon.viewModel.AuthViewModelFactory
import com.example.dfhackthon.viewModel.RegisterViewModel

class AvailableSeatActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAvailableSeatBinding
    private lateinit var availableSeatAdapter: AvailableSeatAdapter
    private lateinit var bindingDialog: DialogItemBoxLayoutBinding
    private var list = ArrayList<Availability>()
    private var selectType = ""
    private var slotID = 0
    private var workSpaceID = 0
    private var workSpaceNAME = ""
    private var slotSelectTime = ""
    private var date = ""

    private val registerViewModel by viewModels<RegisterViewModel> {
        AuthViewModelFactory(Application(), AuthRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityAvailableSeatBinding.inflate(layoutInflater)

        selectType = intent.getStringExtra("selectType").toString()
        slotID = intent.getIntExtra("slotId", 0)
        slotSelectTime = intent.getStringExtra("slotTime").toString()
        date = intent.getStringExtra("date").toString()

        val formattedDate = formatDate(date)
        binding.tvDateTime.text = "$formattedDate $slotSelectTime"

        if (selectType == "1") {
            binding.tvName.text = "Available desks"
        } else {
            binding.tvName.text = "Available rooms"
        }

        observers()
        registerViewModel.availableApi(date, slotID.toString(), selectType)

        binding.btnNext.setOnClickListener {
            if (workSpaceID == 0) {
                Toast.makeText(this, "Please select a available desk.", Toast.LENGTH_SHORT).show()
            } else {
                showCustomDialog()
            }
        }

        binding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private var slotIdCallBack = object : AvailableSeatAdapter.SelectedIdCallBack {
        override fun onClickDate(workSpaceId: Int, workSpaceName: String) {
            workSpaceID = workSpaceId
            workSpaceNAME = workSpaceName
        }
    }

    private fun showCustomDialog() {
        bindingDialog = DialogItemBoxLayoutBinding.inflate(LayoutInflater.from(this))

        val alertDialog = AlertDialog.Builder(this)
            .setView(bindingDialog.root)
            .create()

        alertDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val formattedDate = formatDate(date)
        bindingDialog.tvName.text = "$formattedDate $slotSelectTime"
        bindingDialog.tvRoomID.text = workSpaceID.toString()
        bindingDialog.tvDeskId.text = "DESK $workSpaceNAME"

        bindingDialog.btnConfirm.setOnClickListener {
            registerViewModel.confirmApi(
                BookingConfirmationRequest(
                    date,
                    slotID.toString(),
                    workSpaceID.toString(),
                    selectType
                )
            )
            alertDialog.dismiss()
        }
        bindingDialog.ivCross.setOnClickListener {
            alertDialog.dismiss()
        }
        alertDialog.show()
    }

    private fun observers() {
        registerViewModel.availsResponse.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progress.visibility = View.GONE
                    list.clear()
                    list.addAll(it.data?.availability!!)
                    availableSeatAdapter = AvailableSeatAdapter(this, list, slotIdCallBack)
                    binding.rvSlots.adapter = availableSeatAdapter
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
        registerViewModel.bookingConfResponse.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progress.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finishAffinity()
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