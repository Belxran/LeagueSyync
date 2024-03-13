package com.example.leaguesyync

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.leaguesyync.databinding.ActivityBuscarLigaBinding

class BuscarLigaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBuscarLigaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuscarLigaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar el botón de búsqueda
        binding.buttonBuscar.setOnClickListener {
            // Aquí puedes implementar la lógica para buscar la liga utilizando el código ingresado
            // por el usuario en el EditText
            val codigoLiga = binding.editTextCodigoLiga.text.toString()
            // Lógica de búsqueda de la liga...
        }
    }
}
