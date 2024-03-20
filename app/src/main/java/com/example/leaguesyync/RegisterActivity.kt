package com.example.leaguesyync

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class RegisterActivity : AppCompatActivity() {

    private lateinit var registroManager: RegisterUsuarioManager
    private lateinit var usuarios: MutableList<Usuario>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registroManager = RegisterUsuarioManager(this)
        usuarios = cargarUsuariosDesdeJSON()

        val btnSave = findViewById<Button>(R.id.registerButton)
        btnSave.setOnClickListener {
            registrarUsuario()
        }

        val fechaNacimientoEditText = findViewById<EditText>(R.id.fechaNacimiento)
        fechaNacimientoEditText.setOnClickListener {
            mostrarDatePickerDialog()
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

        if (usuarios.any { it.username == username }) {
            mostrarMensajeError("El nombre de usuario ya está en uso.")
            return
        }

        if (usuarios.any { it.email == email }) {
            mostrarMensajeError("El correo electrónico ya está registrado.")
            return
        }

        val nuevoUsuario = Usuario(
            fullname = "$firstName $lastName1 $lastName2",
            username = username,
            birthdate = fechaNacimiento,
            email = email,
            contraseña = password
        )

        usuarios.add(nuevoUsuario)
        guardarUsuariosEnJSON(usuarios)

        mostrarMensajeExito("Usuario registrado exitosamente.")
    }

    private fun guardarUsuariosEnJSON(usuarios: MutableList<Usuario>) {
        val jsonString = Gson().toJson(usuarios)
        try {
            val outputStream = openFileOutput("usuarios.json", Context.MODE_PRIVATE)
            val writer = BufferedWriter(OutputStreamWriter(outputStream))
            writer.write(jsonString)
            writer.close()
        } catch (e: IOException) {
            e.printStackTrace()
            mostrarMensajeError("Error al guardar usuarios en el archivo JSON.")
        }
    }

    private fun cargarUsuariosDesdeJSON(): MutableList<Usuario> {
        val jsonString = leerArchivoJSON("usuarios.json")
        return if (jsonString.isNotEmpty()) {
            Gson().fromJson(jsonString, object : TypeToken<MutableList<Usuario>>() {}.type)
        } else {
            mutableListOf()
        }
    }

    private fun leerArchivoJSON(filename: String): String {
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
            e.printStackTrace()
            ""
        }
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





    private fun mostrarDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            this,
            { view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, monthOfYear, dayOfMonth)
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val formattedDate = sdf.format(selectedDate.time)
                findViewById<EditText>(R.id.fechaNacimiento).setText(formattedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }
}

