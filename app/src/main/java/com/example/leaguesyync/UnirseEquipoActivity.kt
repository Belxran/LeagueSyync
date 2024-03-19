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
        Equipo(
            id = 1,
            nom = "Equip 1",
            lliga = Lliga(1, "Liga 1", Esport(1,"Futbol", 2)),
            codiEquip = "1",
            mascota = "Colom",
            color1 = "Blau",
            color2 = "Vermell",
            escut = "escut",
            partitsGuanyats = 10,
            partitsEmpatats = 2,
            partitsPerduts = 6
        )
    )

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
