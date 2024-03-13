package com.example.leaguesyync


import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class CrearEquiposActivity : AppCompatActivity() {

    private lateinit var equiposAdapter: EquiposAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_equipos)

        val numEquipos = intent.getIntExtra("NUMERO_EQUIPOS", 0)
        val equipos = mutableListOf<String>()
        for (i in 1..numEquipos) {
            equipos.add("Equipo $i")
        }

        val recyclerViewEquipos = findViewById<RecyclerView>(R.id.recyclerViewEquipos)
        equiposAdapter = EquiposAdapter(equipos)
        recyclerViewEquipos.adapter = equiposAdapter
        recyclerViewEquipos.layoutManager = GridLayoutManager(this, 2)


        findViewById<TextView>(R.id.buttonConfirmarEquipos).setOnClickListener {
            finish()
        }
    }

}
