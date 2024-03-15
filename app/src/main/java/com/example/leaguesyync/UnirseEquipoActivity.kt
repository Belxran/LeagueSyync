
/*7package com.example.leaguesyync


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leaguesyync.databinding.ActivityUnirseEquipoBinding

class UnirseEquipoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUnirseEquipoBinding
    private lateinit var adapter: EquipoAdapter

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
        adapter = EquipoAdapter(equiposDisponibles)
        binding.recyclerViewEquipos.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewEquipos.adapter = adapter

        adapter.setOnItemClickListener(object : EquipoAdapter.OnItemClickListener {
            override fun onItemClick(equipo: Equipo) {
                val intent = Intent(this@UnirseEquipoActivity, CrearAvatarActivity::class.java)
                startActivity(intent)
            }
        })
    }}*/

