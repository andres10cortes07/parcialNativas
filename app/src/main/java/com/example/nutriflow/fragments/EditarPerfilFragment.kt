package com.example.nutriflow.fragments

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.nutriflow.R

class EditarPerfilFragment : Fragment() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var btnGuardarPerfil: Button
    private lateinit var nombres: EditText
    private lateinit var edad: EditText
    private lateinit var correo: EditText
    private lateinit var programa: EditText
    private lateinit var semestre: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_editar_perfil, container, false)

        // Acceder a los componentes de la vista
        btnGuardarPerfil = view.findViewById(R.id.buttonSaveProfile)
        nombres = view.findViewById(R.id.campoEditarNombre)
        edad = view.findViewById(R.id.campoEditarEdad)
        correo = view.findViewById(R.id.campoEditarCorreo)
        programa = view.findViewById(R.id.campoEditarPrograma)
        semestre = view.findViewById(R.id.campoEditarSemestre)

        // acceder al sharedPreferences y sus datos
        sharedPreferences = requireActivity().getSharedPreferences("UserData", MODE_PRIVATE)
        val nombreRegistrado = sharedPreferences.getString("nombres", "")
        val edadRegistrada = sharedPreferences.getString("edad", "")
        val correoRegistrado = sharedPreferences.getString("correo", "")
        val programaRegistrado = sharedPreferences.getString("programa", "")
        val semestreRegistrado = sharedPreferences.getString("semestre", "")

        // modificacion de valores de los ediText
        nombres.setText(nombreRegistrado)
        edad.setText(edadRegistrada)
        correo.setText(correoRegistrado)
        programa.setText(programaRegistrado)
        semestre.setText(semestreRegistrado)


        btnGuardarPerfil.setOnClickListener {
            if(validateFields()) {
                //Guardar data
                saveData()
                findNavController().navigate(R.id.itemInicioFragment)
            }
        }

        return view
    }


    private fun validateFields() : Boolean {
        val campoNombres = nombres.text.toString().trim()
        val campoEdad = edad.text.toString().trim()
        val campoCorreo = correo.text.toString().trim()
        val campoPrograma = programa.text.toString().trim()
        val campoSemestre = semestre.text.toString().trim()

        if (!validateCharacters(campoNombres, 1, 100, "El nombre debe tener entre 1 y 100 caracteres")) return false
        if (!validateCharacters(campoEdad, 1, 3, "La edad debe tener entre 1 y 3 caracteres")) return false
        if (!validateCharacters(campoCorreo, 1, 100, "El campo correo no cumple con el formato requerido")) return false
        if (!validateCharacters(campoPrograma, 1, 100, "El campo programa no cumple con el formato requerido")) return false
        if (!validateCharacters(campoSemestre, 1, 100, "El campo semestre no cumple con el formato requerido")) return false

        if (!campoCorreo.contains("@") || !campoCorreo.contains(".")) {
            Toast.makeText(requireContext(), "El correo ingresado es invalido", Toast.LENGTH_SHORT).show()
            return false
        }


        return true
    }

    private fun validateCharacters(campo : String, min : Byte, max : Int, msgError : String) : Boolean {
        if (campo.length < min || campo.length > max) {
            Toast.makeText(requireContext(), msgError, Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun saveData() {
        // el shared preferences hace las veces de bd
        val editor = sharedPreferences.edit()

        editor.putString("nombres", nombres.text.toString().trim())
        editor.putString("edad", edad.text.toString().trim())
        editor.putString("correo", correo.text.toString().trim())
        editor.putString("programa", programa.text.toString().trim())
        editor.putString("semestre", semestre.text.toString().trim())

        //guardar cambios realizados
        editor.apply()

        Toast.makeText(requireContext(), "Modificacion exitosa", Toast.LENGTH_SHORT).show()
    }
}