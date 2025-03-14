package com.example.nutriflow

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RecoverPassActivity : AppCompatActivity() {

    private lateinit var email : EditText
    private lateinit var btnRecovPass : Button

    override fun onCreate (savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recover_password)

        Log.d("Recover Password Activity", "Oncreate: inicializando activity")

        email = findViewById(R.id.fieldEmailRecoverPass)
        btnRecovPass = findViewById(R.id.buttonRecovPass)

        btnRecovPass.setOnClickListener {
            // Si el correo esta bien validamos si esta registrado
            if (validateEmail()) {
                recoverPassword()
            }
        }
    }

    private fun validateEmail () : Boolean {
        email = findViewById(R.id.fieldEmailRecoverPass)
        val emailUser = email.text.toString().trim()

        if (emailUser.isEmpty() || emailUser.length > 100 || !emailUser.contains("@") || !emailUser.contains(".")) {
            Toast.makeText(this, "El correo ingresado no cumple con el formato ideal", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun recoverPassword () {
        val campoEmail = email.text.toString().trim()
        val sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)
        val emailRegistered = sharedPreferences.getString("correo", "No hay datos registrados")

        if (campoEmail == emailRegistered) {
            Toast.makeText(this, "Hemos enviado un enlace a tu correo", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        else Toast.makeText(this, "El correo ingresado no se encuentra registrado en el sistema", Toast.LENGTH_SHORT).show()
    }
}