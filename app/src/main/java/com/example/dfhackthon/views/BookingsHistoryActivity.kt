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
import com.example.dfhackthon.adapter.BookingsHistoryAdapter
import com.example.dfhackthon.databinding.ActivityBookingsHistoryBinding
import com.example.dfhackthon.model.bookingResponse.Booking
import com.example.dfhackthon.network.AuthRepository
import com.example.dfhackthon.utils.Preference
import com.example.dfhackthon.utils.Status
import com.example.dfhackthon.viewModel.AuthViewModelFactory
import com.example.dfhackthon.viewModel.RegisterViewModel

class BookingsHistoryActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBookingsHistoryBinding
    private lateinit var bookingsHistoryAdapter: BookingsHistoryAdapter
    private var list = ArrayList<Booking>()

    private val registerViewModel by viewModels<RegisterViewModel> {
        AuthViewModelFactory(Application(), AuthRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityBookingsHistoryBinding.inflate(layoutInflater)
        observers()
        registerViewModel.bookingApi("1")

        binding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.ivLogOut.setOnClickListener {
            Preference.getInstance(this).putData(Preference.Keys.isUserLogin, false)
            startActivity(Intent(this,LoginActivity::class.java))
            finishAffinity()
        }

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    private fun observers() {
        registerViewModel.bookingResponse.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progress.visibility = View.GONE
                    list.clear()
                    list.addAll(it.data?.bookings!!)
                    bookingsHistoryAdapter = BookingsHistoryAdapter(this, list)
                    binding.rvBookings.adapter = bookingsHistoryAdapter
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