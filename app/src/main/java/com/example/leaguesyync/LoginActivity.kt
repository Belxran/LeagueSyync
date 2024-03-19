package com.example.leaguesyync

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.gson.Gson
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStreamReader

class LoginActivity : AppCompatActivity() {

    private lateinit var usuarios: List<Usuario>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val facebookButton = findViewById<ImageView>(R.id.facebook_btn)
        val googleButton = findViewById<ImageView>(R.id.google_btn)
        val appleButton = findViewById<ImageView>(R.id.apple_btn)

        // Cargar usuarios desde el archivo JSON
        cargarUsuariosDesdeJSON("usuarios.json")

        loginButton.setOnClickListener {
            // Datos de inicio de sesión de prueba
            val username = "usuario1"
            val contraseña = "contraseña1"

            // Verificación del inicio de sesión
            if (iniciarSesion(username, contraseña)) {
                println("Inicio de sesión exitoso para el usuario: $username")
            } else {
                println("Error: Nombre de usuario o contraseña incorrectos")
            }
        }

    }

    private fun cargarUsuariosDesdeJSON(jsonPath: String) {
        val gson = Gson()
        val inputStream = assets.open(jsonPath)
        val reader = BufferedReader(InputStreamReader(inputStream))
        usuarios = gson.fromJson(reader, Array<Usuario>::class.java).toList()
    }

    private fun iniciarSesion(username: String, contraseña: String): Boolean {
        val usuario = usuarios.find { it.username == username }
        return usuario?.contraseña == contraseña
    }
}

