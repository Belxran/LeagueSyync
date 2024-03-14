package com.example.leaguesyync

// OpcionesActivity.kt

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.leaguesyync.databinding.ActivityOpcionesBinding

class OpcionesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityOpcionesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val ligaNombre = intent.getStringExtra("liga_nombre")

        binding.textViewTituloLiga.text = ligaNombre

        binding.buttonUnirseEquipo.setOnClickListener {

            //val intent = Intent(this, UnirseEquipoActivity::class.java)
            //startActivity(intent)
        }

        binding.buttonVerJornadas.setOnClickListener {

            val intent = Intent(this, VerJornadasActivity::class.java)
            startActivity(intent)
        }

        binding.buttonVerClasificacion.setOnClickListener {

            val intent = Intent(this, VerClasificacionActivity::class.java)
            startActivity(intent)
        }

        binding.buttonEditarAvatar.setOnClickListener {

            val intent = Intent(this, EditarAvatarActivity::class.java)
            startActivity(intent)
        }
    }
}
