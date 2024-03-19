package com.example.leaguesyync


import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CrearEquiposActivity : AppCompatActivity() {

    private lateinit var equiposAdapter: EquipoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_equipos)

        val numEquipos = intent.getIntExtra("NUMERO_EQUIPOS", 0)
        val equipos = mutableListOf<Equipo>()
        for (i in 1..numEquipos) {
            equipos.add(Equipo(  id = 1,
                nom = "Equip 1",
                lliga = Lliga(1, "Liga 1", Esport(1,"Futbol", 16)),
                codiEquip = "1",
                mascota = "null",
                color1 = "null",
                color2 = "null",
                escut = "null",
                partitsGuanyats = 0,
                partitsEmpatats = 0,
                partitsPerduts = 0))
        }

        val recyclerViewEquipos = findViewById<RecyclerView>(R.id.recyclerViewEquipos)

        val listener: (Equipo) -> Unit = { equipo -> mostrarDialogoEditarNombreEquipo(equipo) }

        equiposAdapter = EquipoAdapter(equipos, listener)

        recyclerViewEquipos.adapter = equiposAdapter
        recyclerViewEquipos.layoutManager = GridLayoutManager(this, 2)

        findViewById<TextView>(R.id.buttonConfirmarEquipos).setOnClickListener {
            finish()
        }
    }

    private fun mostrarDialogoEditarNombreEquipo(equipo: Equipo) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Editar nombre del equipo")

        val input = EditText(this)
        input.setText(equipo.nom)
        builder.setView(input)

        builder.setPositiveButton("Aceptar") { dialog, which ->
            val newName = input.text.toString()
            equipo.nom = newName
            equiposAdapter.notifyDataSetChanged()
        }
        builder.setNegativeButton("Cancelar") { dialog, which -> dialog.cancel() }

        builder.show()
    }
}
