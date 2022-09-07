package com.example.projekt_zespolowy_and.login_register

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.projekt_zespolowy_and.HomeFragment
import com.example.projekt_zespolowy_and.MainActivity
import com.example.projekt_zespolowy_and.databinding.ActivityLoginRegisterBinding


class LoginRegister: AppCompatActivity() {

    private lateinit var button_login: Button
    private lateinit var button_register: Button
    private lateinit var button_sign_in: Button

    private lateinit var binding: ActivityLoginRegisterBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //var view = R.layout.activity_login_register
        binding = ActivityLoginRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.buttonSignIn.setOnClickListener {
            SignInFragment().show(supportFragmentManager, "newLoginTag")
        }

        loginViewModel.logged.observe(this){
            if(loginViewModel.logged.value == true){
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("Logged","yes")
                startActivity(intent)
                Toast.makeText(this, "True!", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "False!", Toast.LENGTH_SHORT).show()
            }
            }
            //binding.taskName.text = String.format("Task name: %s",it)
        }

        /*
        button_login = findViewById(R.id.button_register)
        button_login.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, HomeFragment()).addToBackStack(null)
                .commit()
        }

        button_sign_in = findViewById(R.id.button_sign_in)
        button_sign_in.setOnClickListener {

        }

        button_register = findViewById(R.id.button_register)
        button_register.setOnClickListener {

        }*/

    private fun login() {
        //button_login.isEnabled = false
        //button_login.alpha = 0.5f
        //Toast.makeText(context,"Zalogowano", Toast.LENGTH_SHORT).show()
        //var navHome = activity as FragmentNavigation
        //navHome.navigateFrag(HomeFragment(),true)
    }

    private fun register(){}

    private fun sign_in(){}
    }
