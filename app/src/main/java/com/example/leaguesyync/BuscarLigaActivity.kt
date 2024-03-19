package com.example.leaguesyync

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.leaguesyync.databinding.ActivityBuscarLigaBinding

class BuscarLigaActivity : AppCompatActivity() {
    private val ligasDeEjemplo = listOf(
        Lliga(1, "LigaPolitecnics", Esport(1, "Futbol", 11)),
        Lliga(2, "LigaKintos01", Esport(1, "Futbol", 11)),
        Lliga(3, "LigaPolitecnics3", Esport(1, "Futbol", 11)),
    )

    private lateinit var binding: ActivityBuscarLigaBinding
    private var ligaEncontrada: Lliga? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBuscarLigaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonBuscar.setOnClickListener {
            val id = binding.editTextCodigoLiga.text.toString()
            val ligasEncontradas = obtenerLigasEncontradas(id)
            if (ligasEncontradas.isNotEmpty()) {
                mostrarCardViewLiga(ligasEncontradas.first())
            } else {
                mostrarErrorLigaNoEncontrada()
            }
        }
    }



    private fun obtenerLigasEncontradas(id: String): List<Lliga> {
        val idInt = id.toIntOrNull()
        return if (idInt != null) {
            ligasDeEjemplo.filter { it.id == idInt }
        } else {
            emptyList()
        }
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

    private fun mostrarCardViewLiga(lliga: Lliga) {
        val codigo = binding.editTextCodigoLiga.text.toString()
        val ligasEncontradas: List<Lliga> = obtenerLigasEncontradas(codigo)

        ligaEncontrada = lliga

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewLigas)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = LigaAdapter(ligasEncontradas)
        recyclerView.adapter = adapter

        adapter.setOnItemClickListener(object : LigaAdapter.OnItemClickListener {
            override fun onItemClick(liga: Lliga) {
                mostrarDialogoUnirseALiga(liga) // Pasar la liga seleccionada como parámetro
            }
        })
    }

    fun mostrarDialogoUnirseALiga(lliga: Lliga) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Unirse a la Liga")
        alertDialogBuilder.setMessage("¿Quieres unirte a la Liga ${lliga.nom}?") // Usar el nombre de la liga seleccionada
        alertDialogBuilder.setPositiveButton("Sí") { dialog, which ->
            // Implementa aquí la lógica para unirse a la liga
            dialog.dismiss()
        }
        alertDialogBuilder.setNegativeButton("No") { dialog, which ->
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }}