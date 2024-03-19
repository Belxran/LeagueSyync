package com.example.leaguesyync

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class RegisterActivity : AppCompatActivity() {

    private lateinit var registroManager: RegisterUsuarioManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registroManager = RegisterUsuarioManager(this)

        val btnSave = findViewById<Button>(R.id.registerButton)
        btnSave.setOnClickListener {
            registrarUsuario()
        }
    }

    private fun registrarUsuario() {
        val firstName = findViewById<EditText>(R.id.firstNameEditText).text.toString()
        val lastName1 = findViewById<EditText>(R.id.lastName1EditText).text.toString()
        val lastName2 = findViewById<EditText>(R.id.lastName2EditText).text.toString()
        val username = findViewById<EditText>(R.id.usernameEditText).text.toString()
        val password = findViewById<EditText>(R.id.passwordEditText).text.toString()
        val passwordConfirm = findViewById<EditText>(R.id.passwordConfirmEditText).text.toString()
        val email = findViewById<EditText>(R.id.email).text.toString()
        val fechaNacimiento = findViewById<EditText>(R.id.fechaNacimiento).text.toString()

        if (username.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty() || email.isEmpty() || fechaNacimiento.isEmpty()) {
            mostrarMensajeError("Por favor, complete todos los campos.")
            return
        }

        if (password != passwordConfirm) {
            mostrarMensajeError("Las contraseñas no coinciden.")
            return
        }

        if (registroManager.getUsuariosRegistrados().any { it.username == username }) {
            mostrarMensajeError("El nombre de usuario ya está en uso.")
            return
        }

        if (registroManager.getUsuariosRegistrados().any { it.email == email }) {
            mostrarMensajeError("El correo electrónico ya está registrado.")
            return
        }

        val usuario = Usuario(
            fullname = "$firstName $lastName1 $lastName2",
            username = username,
            birthdate = fechaNacimiento,
            email = email,
            contraseña = password
        )

        registroManager.registrarNuevoUsuario(usuario)

        mostrarMensajeExito("Usuario registrado exitosamente.")
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
                val intent = Intent(this, MenuPrincipal::class.java)
                startActivity(intent)
                finish()
            }
            .show()
    }
}

