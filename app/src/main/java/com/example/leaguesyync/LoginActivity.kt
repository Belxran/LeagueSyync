package com.example.leaguesyync

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.IOException
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

        cargarUsuariosDesdeJSON()
    }

    private fun cargarUsuariosDesdeJSON() {
        val jsonString = leerArchivoJSON("usuarios.json")
        if (jsonString.isNotEmpty()) {
            usuarios = Gson().fromJson(jsonString, Array<Usuario>::class.java).toList()
            Log.d("LoginActivity", "Usuarios cargados correctamente")
        } else {
            Log.e("LoginActivity", "No se pudo cargar el archivo JSON")
        }
    }

    private fun leerArchivoJSON(filename: String): String {
        return try {
            val inputStream = assets.open(filename)
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            val stringBuilder = StringBuilder()
            var line: String? = bufferedReader.readLine()
            while (line != null) {
                stringBuilder.append(line)
                line = bufferedReader.readLine()
            }
            bufferedReader.close()
            stringBuilder.toString()
        } catch (e: IOException) {
            e.printStackTrace()
            ""
        }
    }
}
