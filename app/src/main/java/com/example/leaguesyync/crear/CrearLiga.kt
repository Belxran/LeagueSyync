package com.example.leaguesyync

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.leaguesyync.R
import com.example.leaguesyync.databinding.CrearLigaBinding


class CrearLiga : AppCompatActivity() {
    private lateinit var binding: CrearLigaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CrearLigaBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.buttonCreateLeague.setOnClickListener {
            val numEquipos = binding.spinnerNumTeams.selectedItem.toString().toInt()
            val intent = Intent(this, CrearEquiposActivity::class.java)
            intent.putExtra("NUMERO_EQUIPOS", numEquipos)
            startActivity(intent)
        }

        var  back_Button: ImageButton = findViewById(R.id.back_button)

        back_Button.setOnClickListener{
            onBackPressed()
        }
    }
}
