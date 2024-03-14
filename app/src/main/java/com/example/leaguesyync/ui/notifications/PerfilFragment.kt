package com.example.leaguesyync.ui.notifications

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.leaguesyync.R

class PerfilFragment : Fragment() {

    private lateinit var buttonChangePassword: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_perfil, container, false)

        buttonChangePassword = view.findViewById(R.id.cambiarcontrasena_button)

        buttonChangePassword.setOnClickListener {
        showChangePasswordDialog()
        }

         return view
    }

    private fun showChangePasswordDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_change_password, null)
        val editTextCurrentPassword = dialogView.findViewById<EditText>(R.id.editText_current_password)
        val editTextNewPassword = dialogView.findViewById<EditText>(R.id.editText_new_password)
        val editTextConfirmNewPassword = dialogView.findViewById<EditText>(R.id.editText_confirm_new_password)

        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle("Cambiar contraseña")
            .setPositiveButton("Cambiar") { dialog, which ->
                val currentPassword = editTextCurrentPassword.text.toString()
                val newPassword = editTextNewPassword.text.toString()
                val confirmNewPassword = editTextConfirmNewPassword.text.toString()

                if (newPassword == confirmNewPassword) {
                    // Aquí puedes llamar a una función o método para cambiar la contraseña
                    // por ejemplo: changePassword(currentPassword, newPassword)
                } else {
                    // Muestra un mensaje de error si las contraseñas no coinciden
                    // Por ejemplo: showToast("Las contraseñas no coinciden")
                }
            }
            .setNegativeButton("Cancelar", null)

        val alertDialog = dialogBuilder.create()
        alertDialog.show()
    }
}
