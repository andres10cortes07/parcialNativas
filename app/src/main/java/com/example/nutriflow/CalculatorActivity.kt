package com.example.nutriflow

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow

class CalculatorActivity : AppCompatActivity() {

    private lateinit var monto : EditText
    private lateinit var tasa : EditText
    private lateinit var plazo : EditText
    private lateinit var amortizacion : EditText
    private lateinit var btnCalcular : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        Log.d("CalculatorActivity", "Oncreate: inicializando activity")

        monto = findViewById(R.id.fieldMonto)
        tasa = findViewById(R.id.fieldInteres)
        plazo = findViewById(R.id.fieldPlazo)
        amortizacion = findViewById(R.id.fieldTipo)
        btnCalcular = findViewById(R.id.buttonCalculate)

        btnCalcular.setOnClickListener {
            val campoMonto = monto.text.toString().toInt()
            val campoTasa = tasa.text.toString().toInt()
            val campoPlazo = plazo.text.toString().toInt()
            val campoAmort = amortizacion.text.toString()

        }


    }
}