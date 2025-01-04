package com.example.dfhackthon.views

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dfhackthon.R
import com.example.dfhackthon.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var selectType = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

       binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.clDesk.setOnClickListener {
            selectType = "1"
            binding.clDesk.setBackgroundResource(R.drawable.blue_bg)
            binding.clMeeting.setBackgroundResource(R.drawable.light_blue_bg)
            val intent = Intent(this, SelectDateSlotsActivity::class.java)
            intent.putExtra("selectType", selectType)
            startActivity(intent)
        }

        binding.clMeeting.setOnClickListener {
            selectType = "2"
            binding.clDesk.setBackgroundResource(R.drawable.light_blue_bg)
            binding.clMeeting.setBackgroundResource(R.drawable.blue_bg)
            val intent = Intent(this, SelectDateSlotsActivity::class.java)
            intent.putExtra("selectType", selectType)
            startActivity(intent)
        }

        binding.mbBooking.setOnClickListener {
            startActivity(Intent(this, BookingsHistoryActivity::class.java))
        }
    }
}