package com.example.leaguesyync.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.leaguesyync.Esport
import com.example.leaguesyync.LigaAdapter
import com.example.leaguesyync.Lliga
import com.example.leaguesyync.R

class EquipoFragment : Fragment() {

    private val ligasDeEjemplo = listOf(
        Lliga(1, "LigaPolitecnics", Esport(1, "Futbol", 11)),
        Lliga(2, "LigaKintos01", Esport(1, "Futbol", 11)),
        Lliga(3, "LigaPolitecnics3", Esport(1, "Futbol", 11)),
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
