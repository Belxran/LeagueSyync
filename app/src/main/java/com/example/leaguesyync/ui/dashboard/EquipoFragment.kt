package com.example.leaguesyync.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.leaguesyync.databinding.FragmentEscudoBinding

class EquipoFragment : Fragment() {

    private var _binding: FragmentEscudoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEscudoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // No es necesario ViewModel en este fragmento

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
