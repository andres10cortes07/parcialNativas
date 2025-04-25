package com.example.nutriflow.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nutriflow.R

class LoginActivity : AppCompatActivity() {

    private lateinit var btnLogin : Button
    private lateinit var email : EditText
    private lateinit var password : EditText
    private lateinit var txtRecovPass : TextView
    private lateinit var txtRegister : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        Log.d("LoginActivity", "Oncreate: inicializando activity")

        btnLogin = findViewById(R.id.buttonLogin)
        email = findViewById(R.id.fieldUserLogin)
        password = findViewById(R.id.fieldPasswordLogin)
        txtRecovPass = findViewById(R.id.recovPassLogin)
        txtRegister = findViewById(R.id.registerHome)

        btnLogin.setOnClickListener {
            if (validateCredentials()) {
                Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            else Toast.makeText(this, "Los datos ingresados son erroneos", Toast.LENGTH_SHORT).show()
        }

        txtRecovPass.setOnClickListener {
            val intent = Intent(this, RecoverPassActivity::class.java)
            startActivity(intent)
            finish()
        }

        txtRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun validateCredentials () : Boolean {
        val campoEmail = email.text.toString().trim()
        val campoPassword = password.text.toString().trim()

        val sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)
        val emailRegistered = sharedPreferences.getString("correo", "")
        val passwordRegistered = sharedPreferences.getString("contraseña", "")

        if(emailRegistered == campoEmail && passwordRegistered == campoPassword) return true
        return false
    }
}