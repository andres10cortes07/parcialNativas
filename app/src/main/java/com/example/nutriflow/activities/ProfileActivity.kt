package com.example.nutriflow.activities

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.nutriflow.R

class ProfileActivity : AppCompatActivity() {

    private lateinit var nombre : TextView
    private lateinit var correo : TextView
    private lateinit var telefono : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        Log.d("Profile Activity", "Oncreate: inicializando activity")

        nombre = findViewById(R.id.nameProfile)
        correo = findViewById(R.id.emailUserProfile)
        telefono = findViewById(R.id.phoneUserProfile)

        chargeInfo()
    }

    private fun chargeInfo () {
        val sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE)
        val nameRegistered = sharedPreferences.getString("nombres", "") + " " + sharedPreferences.getString("apellidos", "")
        val emailRegistered = sharedPreferences.getString("correo", "")
        val phoneRegistered = sharedPreferences.getString("telefono", "")

        nombre.text = nameRegistered
        correo.text = emailRegistered
        telefono.text = phoneRegistered
    }
}