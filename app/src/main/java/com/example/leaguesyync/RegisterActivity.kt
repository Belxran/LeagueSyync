package com.example.leaguesyync

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class RegisterActivity : AppCompatActivity() {

    private lateinit var firstNameEditText: EditText
    private lateinit var lastName1EditText: EditText
    private lateinit var lastName2EditText: EditText
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var passwordConfirmEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var fechaNacimientoEditText: EditText
    private lateinit var registerButton: Button

    private lateinit var usersList: List<Usuario>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        firstNameEditText = findViewById(R.id.firstNameEditText)
        lastName1EditText = findViewById(R.id.lastName1EditText)
        lastName2EditText = findViewById(R.id.lastName2EditText)
        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        passwordConfirmEditText = findViewById(R.id.passwordConfirmEditText)
        emailEditText = findViewById(R.id.email)
        fechaNacimientoEditText = findViewById(R.id.fechaNacimiento)
        registerButton = findViewById(R.id.registerButton)

        loadUsersFromJson()

        registerButton.setOnClickListener { registerUser() }

        fechaNacimientoEditText.setOnClickListener { showDatePicker() }
    }

    private fun loadUsersFromJson() {
        try {
            val inputStream = assets.open("users.json")
            val reader = BufferedReader(InputStreamReader(inputStream))
            val jsonStringBuilder = StringBuilder()
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                jsonStringBuilder.append(line)
            }
            reader.close()

            val userListType = object : TypeToken<List<Usuario>>() {}.type
            usersList = Gson().fromJson(jsonStringBuilder.toString(), userListType)
        } catch (e: Exception) {
            e.printStackTrace()
            usersList = emptyList()
        }
    }

    private fun registerUser() {
        val fullName = "${firstNameEditText.text} ${lastName1EditText.text} ${lastName2EditText.text}"
        val username = usernameEditText.text.toString()
        val password = passwordEditText.text.toString()
        val confirmPassword = passwordConfirmEditText.text.toString()
        val email = emailEditText.text.toString()
        val fechaNacimiento = fechaNacimientoEditText.text.toString()

        if (usersList.any { it.username == username }) {
            showToast("El nombre de usuario ya está en uso.")
            return
        }

        if (usersList.any { it.email == email }) {
            showToast("El correo electrónico ya está en uso.")
            return
        }

        if (fullName.isBlank() || username.isBlank() || password.isBlank() || confirmPassword.isBlank() || email.isBlank() || fechaNacimiento.isBlank()) {
            showToast("Por favor complete todos los campos.")
            return
        }


        if (password != confirmPassword) {
            showToast("Las contraseñas no coinciden.")
            return
        }

        // TODO: Realizar la lógica de registro de usuario aquí (enviar datos a la base de datos, etc.)

        showToast("Registro exitoso")

        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, dayOfMonth)
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val dateString = dateFormat.format(selectedDate.time)
                fechaNacimientoEditText.setText(dateString)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePicker.show()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
