package com.example.leaguesyync.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.leaguesyync.R
import com.example.leaguesyync.Usuario
import com.example.leaguesyync.databinding.FragmentPerfilBinding

class PerfilFragment : Fragment() {
    private lateinit var fullnameTextView: TextView
    private lateinit var usernameTextView: TextView
    private lateinit var birthdateTextView: TextView
    private lateinit var emailTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_perfil, container, false)

        fullnameTextView = view.findViewById(R.id.textView_fullname)
        usernameTextView = view.findViewById(R.id.textView_username)
        birthdateTextView = view.findViewById(R.id.textView_birthdate)
        emailTextView = view.findViewById(R.id.textView_email)

        val usuario = obtenerDatosDelUsuario()

        usuario?.let {
            fullnameTextView.text = it.fullname
            usernameTextView.text = it.username
            birthdateTextView.text = it.birthdate
            emailTextView.text = it.email
        }

        return view
    }

    private fun obtenerDatosDelUsuario(): Usuario? {
        return null
    }}