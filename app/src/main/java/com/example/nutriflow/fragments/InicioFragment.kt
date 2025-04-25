package com.example.nutriflow.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import androidx.fragment.app.Fragment
import com.example.nutriflow.R

class InicioFragment : Fragment() {

    private lateinit var msgCustom : TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_inicio, container, false)
        msgCustom = view.findViewById(R.id.MsgCustomStart)

        chargeMessage(msgCustom)

        return view
    }

    private fun chargeMessage(msgCustom : TextView) {
        val sharedPreferences = requireActivity().getSharedPreferences("UserData", MODE_PRIVATE)
        val nameRegistered = sharedPreferences.getString("nombres", "")

        msgCustom.text = "Bienvenid@ ${nameRegistered}!!!"

    }

}