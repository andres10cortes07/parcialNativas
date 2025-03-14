package com.example.nutriflow

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    //    Se definen las variables que se manejarán
    private lateinit var nombres : EditText
    private lateinit var apellidos : EditText
    private lateinit var correo : EditText
    private lateinit var telefono : EditText
    private lateinit var contraseña : EditText
    private lateinit var validacionContraseña : EditText
    private lateinit var boton : Button
    private lateinit var checkBox : CheckBox

    // se usa para guardar pequeños datos o configuraciones usando clave valor
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        Log.d("ActivityRegister", "OnCreate: Inicializando el activity")

        // inicializacion de variables
        nombres = findViewById(R.id.fieldNameRegister)
        apellidos = findViewById(R.id.fieldLastNamesRegister)
        correo = findViewById(R.id.fieldEmailRegister)
        telefono = findViewById(R.id.fieldPhoneRegister)
        contraseña = findViewById(R.id.fieldPassRegister)
        validacionContraseña = findViewById(R.id.fieldValidatePassRegister)
        boton = findViewById(R.id.buttonRegister)
        checkBox = findViewById(R.id.checkBoxRegister)

        sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE)

        boton.setOnClickListener {
            if(validateFields()) {
                //Guardar data
                saveData()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun validateFields() : Boolean {
        val campoNombres = nombres.text.toString().trim()
        val campoApellidos = apellidos.text.toString().trim()
        val campoCorreo = correo.text.toString().trim()
        val campoTelefono = telefono.text.toString().trim()
        val campoContraseña = contraseña.text.toString().trim()
        val campoValContraseña = validacionContraseña.text.toString().trim()

        if (!validateCharacters(campoNombres, 1, 100, "El nombre debe tener entre 1 y 100 caracteres")) return false
        if (!validateCharacters(campoApellidos, 1, 100, "El apellido debe tener entre 1 y 100 caracteres")) return false
        if (!validateCharacters(campoCorreo, 1, 100, "El campo correo no cumple con el formato requerido")) return false

        if (!campoCorreo.contains("@") || !campoCorreo.contains(".")) {
            Toast.makeText(this, "El correo ingresado es invalido", Toast.LENGTH_SHORT).show()
            return false
        }

        if (campoTelefono.length != 10) {
            Toast.makeText(this, "El telefono debe contar con 10 caracteres", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!validateCharacters(campoContraseña, 1, 100, "La contraseña debe tener entre 1 y 100 caracteres")) return false
        if (!validateCharacters(campoValContraseña, 1, 100, "La contraseña debe tener entre 1 y 100 caracteres")) return false

        if (campoContraseña != campoValContraseña) {
            Toast.makeText(this, "Las contraseñas ingresadas son diferentes", Toast.LENGTH_SHORT).show()
            return false
        }
        if (!checkBox.isChecked()){
            Toast.makeText(this, "Debes aceptar los terminos y condiciones para registrarte", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun validateCharacters(campo : String, min : Byte, max : Int, msgError : String) : Boolean {
        if (campo.length < min || campo.length > max) {
            Toast.makeText(this, msgError, Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun saveData() {
        // el shared preferences hace las veces de bd
        val editor = sharedPreferences.edit()

        editor.putString("nombres", nombres.text.toString().trim())
        editor.putString("apellidos", apellidos.text.toString().trim())
        editor.putString("correo", correo.text.toString().trim())
        editor.putString("telefono", telefono.text.toString().trim())
        editor.putString("contraseña", contraseña.text.toString().trim())

        //guardar cambios realizados
        editor.apply()

        Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
    }

}