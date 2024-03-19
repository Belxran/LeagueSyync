package com.example.leaguesyync

data class Avatar(
    val id: Int,
    val sobrenom: String,
    val dorsal: Int?,
    val equip: Equipo,
    val usuari: Usuario,
    val descripcio: String?,
    val lliga: Liga?
)
