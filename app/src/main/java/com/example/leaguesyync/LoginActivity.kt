package com.example.leaguesyync

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
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

        cargarUsuariosDesdeFiles()

        val loginButton = findViewById<Button>(R.id.loginButton)
        val facebookButton = findViewById<ImageView>(R.id.facebook_btn)
        val googleButton = findViewById<ImageView>(R.id.google_btn)
        val appleButton = findViewById<ImageView>(R.id.apple_btn)

        loginButton.setOnClickListener {
            val username = obtenerTexto(R.id.usernameEditText)
            val password = obtenerTexto(R.id.passwordEditText)
            if (username.isNotEmpty() && password.isNotEmpty()) {
                val usuario = usuarios.find { it.username == username && it.contraseña == password }
                if (usuario != null) {
                    // Iniciar sesión exitosamente
                    val intent = Intent(this, MenuPrincipal::class.java)
                    intent.putExtra("USUARIO", usuario)
                    startActivity(intent)
                    finish()
                } else {
                    mostrarMensaje("Credenciales inválidas")
                }
            } else {
                mostrarMensaje("Por favor, completa todos los campos")
            }
        }
    }

    private fun cargarUsuariosDesdeFiles() {
        val jsonString = leerArchivoJSONFromFiles("usuarios.json")
        if (jsonString.isNotEmpty()) {
            usuarios = Gson().fromJson(jsonString, Array<Usuario>::class.java).toList()
            Log.d("LoginActivity", "Usuarios cargados correctamente desde archivos locales")
        } else {
            Log.d("LoginActivity", "No se encontró el archivo de usuarios en archivos locales. Cargando desde assets.")
            cargarUsuariosDesdeAssets()
        }
    }

    private fun cargarUsuariosDesdeAssets() {
        val jsonString = leerArchivoJSONFromAssets("usuarios.json")
        if (jsonString.isNotEmpty()) {
            usuarios = Gson().fromJson(jsonString, Array<Usuario>::class.java).toList()
            Log.d("LoginActivity", "Usuarios cargados correctamente desde assets")
        } else {
            Log.e("LoginActivity", "No se pudo cargar el archivo JSON desde assets")
        }
    }

    private fun leerArchivoJSONFromFiles(filename: String): String {
        return try {
            val inputStream = openFileInput(filename)
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
            Log.e("LoginActivity", "Error al leer el archivo JSON desde archivos locales: $e")
            ""
        }
    }

    private fun leerArchivoJSONFromAssets(filename: String): String {
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
            Log.e("LoginActivity", "Error al leer el archivo JSON desde assets: $e")
            ""
        }
    }

    private fun obtenerTexto(id: Int): String {
        return findViewById<EditText>(id).text.toString()
    }

    private fun mostrarMensaje(mensaje: String) {
        Log.e("LoginActivity", mensaje)
    }
}
