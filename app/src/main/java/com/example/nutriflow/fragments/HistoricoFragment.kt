package com.example.nutriflow.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.nutriflow.R
import java.text.NumberFormat
import java.util.Locale

class HistoricoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_historico, container, false)

        // Referencias a los TextViews
        val cuotaTextView = view.findViewById<TextView>(R.id.resultadoCuota)
        val totalTextView = view.findViewById<TextView>(R.id.resultadoTotal)
        val interesesTextView = view.findViewById<TextView>(R.id.resultadoTotalIntereses)

        // Obtener datos de SharedPreferences
        val sharedPref = requireContext().getSharedPreferences("UserData", Context.MODE_PRIVATE)
        val cuota = sharedPref.getFloat("cuotaMensual", 0f)
        val total = sharedPref.getFloat("totalPagar", 0f)
        val intereses = sharedPref.getFloat("intereses", 0f)

        // Colocar los datos en los TextViews
        cuotaTextView.text = NumberFormat.getCurrencyInstance(Locale("es", "CO")).format(cuota)
        totalTextView.text = NumberFormat.getCurrencyInstance(Locale("es", "CO")).format(total)
        interesesTextView.text = NumberFormat.getCurrencyInstance(Locale("es", "CO")).format(intereses)

        return view
    }
}
