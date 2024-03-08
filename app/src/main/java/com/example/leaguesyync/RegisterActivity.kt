package com.example.leaguesyync

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import java.io.File

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnSave = findViewById<Button>(R.id.registerButton)
        val etName = findViewById<EditText>(R.id.firstNameEditText)
        val etLastName1 = findViewById<EditText>(R.id.lastName1EditText)
        val etLastName2 = findViewById<EditText>(R.id.lastName2EditText)
        val etUsername = findViewById<EditText>(R.id.usernameEditText)
        val etPassword = findViewById<EditText>(R.id.confirmPasswordEditText)

        btnSave.setOnClickListener {
            val name = etName.text.toString()
            val lastName1 = etLastName1.text.toString()
            val lastName2 = etLastName2.text.toString()
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()

            // Crear un objeto Usuario con los datos ingresados

            // Guardar el usuario en el archivo JSON

            // Aquí puedes agregar más lógica, como mostrar un mensaje de éxito o redirigir a otra actividad
        }
    }
}
