package com.example.leaguesyync

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val facebookButton = findViewById<ImageView>(R.id.facebook_btn)
        val googleButton = findViewById<ImageView>(R.id.google_btn)
        val appleButton = findViewById<ImageView>(R.id.apple_btn)


        loginButton.setOnClickListener {
            val intent = Intent(this, MenuPrincipal::class.java)
            startActivity(intent)
        }
    }
}