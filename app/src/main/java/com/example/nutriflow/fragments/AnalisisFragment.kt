package com.example.nutriflow.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.nutriflow.R
import kotlin.math.pow

class AnalisisFragment : Fragment() {

    private lateinit var campoMonto: EditText
    private lateinit var campoTasa: EditText
    private lateinit var campoPlazo: EditText
    private lateinit var campoTipo: EditText
    private lateinit var buttonSave: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_analisis, container, false)

        campoMonto = view.findViewById(R.id.campoMonto)
        campoTasa = view.findViewById(R.id.campoTaza)
        campoPlazo = view.findViewById(R.id.campoPlazo)
        campoTipo = view.findViewById(R.id.campoTipo)
        buttonSave = view.findViewById(R.id.buttonSaveCalculo)

        buttonSave.setOnClickListener {
            guardarDatosYCalcular()
        }

        return view
    }

    private fun guardarDatosYCalcular() {
        val montoStr = campoMonto.text.toString()
        val tasaStr = campoTasa.text.toString()
        val plazoStr = campoPlazo.text.toString()

        if (montoStr.isBlank() || tasaStr.isBlank() || plazoStr.isBlank()) {
            Toast.makeText(requireContext(), "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            return
        }

        try {
            val monto = montoStr.toDouble()
            val tasaAnual = tasaStr.toDouble()
            val plazoMeses = plazoStr.toInt()

            val tasaMensual = tasaAnual / 12 / 100
            val i = tasaMensual
            val n = plazoMeses.toDouble()

            // Cuota mensual = P × [i(1+i)^n] / [(1+i)^n - 1]
            val cuotaMensual = monto * (i * (1 + i).pow(n)) / ((1 + i).pow(n) - 1)
            val totalPagar = cuotaMensual * n
            val intereses = totalPagar - monto

            // Guardar en SharedPreferences
            val shared = requireContext().getSharedPreferences("UserData", Context.MODE_PRIVATE)

            val editor = shared.edit()


            editor.putFloat("monto", monto.toFloat())
            editor.putFloat("tasa", tasaAnual.toFloat())
            editor.putInt("plazo", plazoMeses)
            editor.putFloat("cuotaMensual", cuotaMensual.toFloat())
            editor.putFloat("totalPagar", totalPagar.toFloat())
            editor.putFloat("intereses", intereses.toFloat())
            editor.apply()

            Toast.makeText(requireContext(), "Datos guardados y cálculo realizado", Toast.LENGTH_SHORT).show()

        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Error en los datos ingresados", Toast.LENGTH_SHORT).show()
        }
    }
}
