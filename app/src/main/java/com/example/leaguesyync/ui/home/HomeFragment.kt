package com.example.leaguesyync

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.leaguesyync.crear.CrearLiga
import com.example.leaguesyync.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Agregar OnClickListener al botón "buscarliga"
        binding.buscarliga.setOnClickListener {
            // Acción a realizar al hacer clic en el CardView
            startActivity(Intent(activity, BuscarLigaActivity::class.java))
        }

        // Agregar OnClickListener al botón "createleague"
        binding.createleague.setOnClickListener {
            Log.d("HomeFragment", "CardView clicked")
            startActivity(Intent(activity, CrearLiga::class.java))
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
