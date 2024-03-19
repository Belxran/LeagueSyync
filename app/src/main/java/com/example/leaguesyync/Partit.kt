package com.example.leaguesyync

data class Partit(
    val id: Int,
    val equip1: Equipo,
    val equip2: Equipo,
    val data: String?,
    val hora: String?,
    val puntsEquip1: Int?,
    val puntsEquip2: Int?
)
