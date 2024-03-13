package com.example.leaguesyync

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etFechaNacimiento = findViewById<EditText>(R.id.fechaNacimiento)
        val calendar = Calendar.getInstance()

        etFechaNacimiento.setOnClickListener {
            val datePicker = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                    etFechaNacimiento.setText(
                        String.format(
                            "%02d/%02d/%02d",
                            dayOfMonth,
                            month + 1,
                            year % 100
                        )
                    )
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
        }

        val btnSave = findViewById<Button>(R.id.registerButton)
        btnSave.setOnClickListener {
            val intent = Intent(this, MenuPrincipal::class.java)
            startActivity(intent)
        }
    }
}
