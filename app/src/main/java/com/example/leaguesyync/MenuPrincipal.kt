package com.example.leaguesyync

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.leaguesyync.databinding.ActivityMenuPrincipalBinding

class MenuPrincipal : AppCompatActivity() {

    private lateinit var binding: ActivityMenuPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMenuPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_menu_principal)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_liga, R.id.navigation_home, R.id.navigation_perfil
            )


        )
        val topLevelDestinations = appBarConfiguration.topLevelDestinations

        for (destinationId in topLevelDestinations) {
            Log.d("AppBarConfiguration", "ID de destino configurado: $destinationId")}

        navView.setupWithNavController(navController)



    }}



