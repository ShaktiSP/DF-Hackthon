package com.example.dfhackthon.views

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.dfhackthon.R
import com.example.dfhackthon.databinding.ActivityLoginBinding
import com.example.dfhackthon.network.AuthRepository
import com.example.dfhackthon.utils.Preference
import com.example.dfhackthon.utils.Status
import com.example.dfhackthon.viewModel.AuthViewModelFactory
import com.example.dfhackthon.viewModel.RegisterViewModel

class LoginActivity : AppCompatActivity() {

    lateinit var binding : ActivityLoginBinding
    private var passwordType = 0

    private val registerViewModel by viewModels<RegisterViewModel> {
        AuthViewModelFactory(Application(), AuthRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginBinding.inflate(layoutInflater)

        val isUserLogin = Preference.getInstance(this).getData(Preference.Keys.isUserLogin, false)
        if (isUserLogin == true) {
            startActivity(Intent(this, MainActivity::class.java))
            finishAffinity()
        }
        handlePassword()
        observers()

        binding.btnCreateAccount.setOnClickListener {
            if (validation()) {
                registerViewModel.logInApi(
                        binding.etFullName.text.toString(),
                        binding.etPass.text.toString()
                )
            }
        }

        binding.tvSignUP.setOnClickListener {
            startActivity(Intent(this, SiginupActivity::class.java))
        }

        binding.ivPass.setOnClickListener {
            handlePassword()
            binding.etPass.setSelection(binding.etPass.length())
        }

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun handlePassword() {
        if (passwordType == 0) {
            binding.ivPass.setImageResource(R.drawable.close_eye)
            binding.etPass.transformationMethod = PasswordTransformationMethod.getInstance()
            passwordType = 1
        } else {
            binding.ivPass.setImageResource(R.drawable.open_eye)
            binding.etPass.transformationMethod = HideReturnsTransformationMethod.getInstance()
            passwordType = 0
        }
    }

    private fun observers() {
        registerViewModel.logInResponse.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    Log.d("TAG", "observers: ${it.message}")
                    Preference.getInstance(this).putData(Preference.Keys.isUserLogin, true)
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finishAffinity()
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
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
                    binding.btnCreateAccount.text = "Log In"
                }
            }
        }
    }


    private fun validation(): Boolean {
        when {
            binding.etFullName.text.toString().trim().isEmpty() -> {
                Toast.makeText(this, "Please enter mobile number or email", Toast.LENGTH_SHORT).show()
                return false
            }

            binding.etPass.text.toString().trim().isEmpty() -> {
                Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show()
                return false
            }
            else -> return true
        }
        return true
    }
}