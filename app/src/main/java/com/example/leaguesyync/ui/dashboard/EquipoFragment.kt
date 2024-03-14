package com.example.leaguesyync.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.leaguesyync.Liga
import com.example.leaguesyync.LigaAdapter
import com.example.leaguesyync.R

class EquipoFragment : Fragment() {

    private val ligasDeEjemplo = listOf(
        Liga("Liga Kintos01", "Descripción de la Liga 1", "123", 4, "FUTBOL"),
        Liga("Liga Trabajo", "Descripción de la Liga 2", "456", 8, "BALONCESTO"),
        Liga("Liga DAM/DAW", "Descripción de la Liga 3", "789", 16, "BALONMANO")
    )

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LigaAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_escudo, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewLigas)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = LigaAdapter(ligasDeEjemplo)
        recyclerView.adapter = adapter
        return view
    }
}
