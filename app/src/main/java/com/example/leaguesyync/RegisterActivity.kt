package com.example.leaguesyync

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnSave = findViewById<Button>(R.id.registerButton)
        val etName = findViewById<EditText>(R.id.firstNameEditText)
        val etLastName1 = findViewById<EditText>(R.id.lastName1EditText)
        val etLastName2 = findViewById<EditText>(R.id.lastName2EditText)
        val etUsername = findViewById<EditText>(R.id.usernameEditText)
        val etPassword = findViewById<EditText>(R.id.passwordEditText)
        val etConfirmPassword = findViewById<EditText>(R.id.passwordConfirmEditText)


        btnSave.setOnClickListener { val intent = Intent(this, MenuPrincipal::class.java)
            startActivity(intent)
            val name = etName.text.toString()
            val lastName1 = etLastName1.text.toString()
            val lastName2 = etLastName2.text.toString()
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()



        }
    }
}
