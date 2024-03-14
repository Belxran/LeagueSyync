package com.example.leaguesyync

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.leaguesyync.databinding.ActivityBuscarLigaBinding

class BuscarLigaActivity : AppCompatActivity() {
    private val ligasDeEjemplo = listOf(
        Liga("Liga Kintos01", "Descripción de la Liga 1", "123", 4, "FUTBOL"),
        Liga("Liga Trabajo", "Descripción de la Liga 2", "456", 8, "BALONCESTO"),
        Liga("Liga DAM/DAW", "Descripción de la Liga 3", "789", 16, "BALONMANO")
    )

    private lateinit var binding: ActivityBuscarLigaBinding
    private var ligaEncontrada: Liga? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuscarLigaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonBuscar.setOnClickListener {
            val codigo = binding.editTextCodigoLiga.text.toString()
            val ligasEncontradas = obtenerLigasEncontradas(codigo)
            if (ligasEncontradas.isNotEmpty()) {
                mostrarCardViewLiga(ligasEncontradas.first())
            } else {
                mostrarErrorLigaNoEncontrada()
            }
        }
    }

    private fun mostrarCardViewLiga(liga: Liga) {
        val codigo = binding.editTextCodigoLiga.text.toString()
        val ligasEncontradas: List<Liga> = obtenerLigasEncontradas(codigo)

        // Asignar el valor de la liga encontrada a la variable
        ligaEncontrada = liga

        // Configurar el RecyclerView y el adaptador
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewLigas)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = LigaAdapter(ligasEncontradas)
        recyclerView.adapter = adapter

        // Manejar el clic en el RecyclerView
        adapter.setOnItemClickListener(object : LigaAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                mostrarDialogoUnirseALiga()
            }
        })
    }

    private fun obtenerLigasEncontradas(codigo: String): List<Liga> {
        return ligasDeEjemplo.filter { it.codigo == codigo }
    }

    private fun mostrarErrorLigaNoEncontrada() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Liga no encontrada")
        alertDialogBuilder.setMessage("No hay ninguna liga con este código.")
        alertDialogBuilder.setPositiveButton("Aceptar") { dialog, which ->
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun mostrarDialogoUnirseALiga() {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Unirse a la Liga")
        alertDialogBuilder.setMessage("¿Quieres unirte a la Liga ${ligaEncontrada?.nombre}?")
        alertDialogBuilder.setPositiveButton("Sí") { dialog, which ->
            // Implementa aquí la lógica para unirse a la liga
            dialog.dismiss()
        }
        alertDialogBuilder.setNegativeButton("No") { dialog, which ->
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}
