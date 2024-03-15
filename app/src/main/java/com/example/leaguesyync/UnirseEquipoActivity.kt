package com.example.leaguesyync

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leaguesyync.databinding.ActivityUnirseEquipoBinding

class UnirseEquipoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUnirseEquipoBinding
    private lateinit var adapter: EquipoAdapter
    private var equipoSeleccionado: Equipo? = null
    private val equiposDisponibles = listOf(
        Equipo("Equipo 1", "url_escudo_1", 20, 10),
        Equipo("Equipo 2", "url_escudo_2", 18, 10),
        Equipo("Equipo 3", "url_escudo_3", 15, 10)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUnirseEquipoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = EquipoAdapter(equiposDisponibles) { equipo ->
            equipoSeleccionado = equipo
            adapter.notifyDataSetChanged()
            mostrarDialogoConfirmacion(equipo)
        }
        binding.recyclerViewEquipos.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewEquipos.adapter = adapter
    }

    private fun mostrarDialogoConfirmacion(equipo: Equipo) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Confirmar unión")
        alertDialogBuilder.setMessage("¿Seguro que quieres unirte al equipo ${equipo.nombre}?")
        alertDialogBuilder.setPositiveButton("Sí") { dialog, which ->
            unirseAlEquipo(equipo)
            dialog.dismiss()
        }
        alertDialogBuilder.setNegativeButton("No") { dialog, which ->
            dialog.dismiss()
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun unirseAlEquipo(equipo: Equipo) {
        // Aquí puedes realizar la lógica pa     2001dfdssdfra unirse al equipo
        // Por ejemplo, puedes abrir la actividad para crear un avatar
        // Pasando el equipo seleccionado como dato extra en el intent
        val intent = Intent(this, CrearAvatarActivity::class.java)
        intent.putExtra("equipo", equipo)
        startActivity(intent)
    }
}
