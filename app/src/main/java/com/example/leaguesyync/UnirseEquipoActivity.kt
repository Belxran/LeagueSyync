package com.example.leaguesyync

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leaguesyync.databinding.ActivityUnirseEquipoBinding

class UnirseEquipoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUnirseEquipoBinding
    private lateinit var adapter: EquipoAdapterUnirse
    private var equipoSeleccionado: Equipo? = null
    private val equiposDisponibles = listOf(
        Equipo(1, "Equip 1", "LligaPolitecnics", 1, "Colom", "Blau", "Vermell", "escut", 10, 2, 6),
        Equipo(2, "Equip 2", , 2, "Colom", "Blau", "Vermell", "escut", 10, 2, 6))


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUnirseEquipoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = EquipoAdapterUnirse(equiposDisponibles) { equipo ->
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
        alertDialogBuilder.setMessage("¿Seguro que quieres unirte al equipo ${equipo.nom}?")
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
        val intent = Intent(this, CrearAvatarActivity::class.java)
        intent.putExtra("equipo", equipo)
        startActivity(intent)
    }
}
