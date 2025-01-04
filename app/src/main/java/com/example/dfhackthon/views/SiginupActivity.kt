package com.example.dfhackthon.views

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dfhackthon.R
import com.example.dfhackthon.databinding.ActivitySiginupBinding
import com.example.dfhackthon.network.AuthRepository
import com.example.dfhackthon.utils.Preference
import com.example.dfhackthon.utils.Status
import com.example.dfhackthon.viewModel.AuthViewModelFactory
import com.example.dfhackthon.viewModel.RegisterViewModel

class SiginupActivity : AppCompatActivity() {

    lateinit var binding : ActivitySiginupBinding

    private val registerViewModel by viewModels<RegisterViewModel> {
        AuthViewModelFactory(Application(), AuthRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySiginupBinding.inflate(layoutInflater)

        observers()

        binding.btnCreateAccount.setOnClickListener {
            if (validation()) {
                registerViewModel.signUpApi(
                    binding.etFullName.text.toString(),
                    binding.etEmail.text.toString(),
                    binding.etMobile.text.toString()
                )
            }
        }

        binding.tvSignUP.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun observers() {
        registerViewModel.signUpResponse.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    Preference.getInstance(this).putData(Preference.Keys.isUserLogin, true)
                    startActivity(Intent(this, MainActivity::class.java))
                    finishAffinity()
                }

                Status.LOADING -> {
                    binding.apply {
                        btnCreateAccount.text = ""
                        binding.progressLogin.visibility = View.VISIBLE
                        btnCreateAccount.isClickable = false
                    }
                }

                Status.ERROR -> {
                    binding.progressLogin.visibility = View.GONE
                    binding.btnCreateAccount.isClickable = true
                    binding.btnCreateAccount.text = "Create an account"
                }
            }
        }
    }

    private fun validation(): Boolean {
        when {
            binding.etFullName.text.toString().trim().isEmpty() -> {
                Toast.makeText(this, "Please enter full name", Toast.LENGTH_SHORT).show()
                return false
            }

            binding.etEmail.text.toString().trim().isEmpty() -> {
                Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show()
                return false
            }

            binding.etMobile.text.toString().trim().isEmpty() -> {
                Toast.makeText(this, "Please enter mobile number", Toast.LENGTH_SHORT).show()
                return false
            }
            else -> return true
        }
        return true
    }
}