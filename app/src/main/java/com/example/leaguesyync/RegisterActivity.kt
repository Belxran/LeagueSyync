package com.example.leaguesyync

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val registerButton = findViewById<Button>(R.id.registerButton)

        registerButton.setOnClickListener {
            // Aquí deberías implementar la lógica para registrar al usuario
            // Puedes realizar la validación de los datos ingresados por el usuario
            // y guardar la información en tu base de datos o en otro sistema de almacenamiento
            // Una vez registrado, puedes abrir la actividad de inicio de sesión o cualquier otra acción que desees
            finish()
        }
    }
}
