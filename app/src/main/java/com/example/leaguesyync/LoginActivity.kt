package com.example.leaguesyync

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader

class LoginActivity : AppCompatActivity() {

    private lateinit var usuarios: List<Usuario>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        cargarUsuariosDesdeJSON()

        val loginButton = findViewById<Button>(R.id.loginButton)
        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            val usuario = usuarios.find { it.username == username && it.contraseña == password }
            if (usuario != null) {
                mostrarMensajeExito("Inicio de sesión exitoso.")
            } else {
                mostrarMensajeError("Nombre de usuario o contraseña incorrectos.")
            }
        }
    }

    private fun cargarUsuariosDesdeJSON() {
        val jsonUsuarios = leerArchivoJSON("usuarios.json")
        usuarios = Gson().fromJson(jsonUsuarios, Array<Usuario>::class.java).toList()
    }

    private fun leerArchivoJSON(nombreArchivo: String): String {
        val bufferedReader = BufferedReader(InputStreamReader(assets.open(nombreArchivo)))
        val stringBuilder = StringBuilder()
        var line: String?
        while (bufferedReader.readLine().also { line = it } != null) {
            stringBuilder.append(line)
        }
        return stringBuilder.toString()
    }

    private fun mostrarMensajeError(mensaje: String) {
        AlertDialog.Builder(this)
            .setTitle("Error")
            .setMessage(mensaje)
            .setPositiveButton("Aceptar", null)
            .show()
    }

    private fun mostrarMensajeExito(mensaje: String) {
        AlertDialog.Builder(this)
            .setTitle("Éxito")
            .setMessage(mensaje)
            .setPositiveButton("Aceptar") { _, _ ->
                // Ir a la actividad del menú principal
                val intent = Intent(this, MenuPrincipal::class.java)
                startActivity(intent)
                finish()
            }
            .show()
    }
}
