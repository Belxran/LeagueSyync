package com.example.leaguesyync

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val registerButton = findViewById<Button>(R.id.registerButton)
        val loginButton = findViewById<Button>(R.id.loginButton)

        registerButton.setOnClickListener {
            // Acción al hacer clic en el botón de registro
            // Aquí puedes abrir la actividad de registro o cualquier otra acción que desees
        }

        loginButton.setOnClickListener {
            // Acción al hacer clic en el botón de inicio de sesión
            // Aquí puedes abrir la actividad de inicio de sesión o cualquier otra acción que desees
        }
    }
}
