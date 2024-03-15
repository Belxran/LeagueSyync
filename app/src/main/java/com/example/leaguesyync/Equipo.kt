package com.example.leaguesyync

import android.os.Parcel
import android.os.Parcelable

data class Equipo(
    var nombre: String,
    val escudoUrl: String,
    val puntos: Int,
    val partidosJugados: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeString(escudoUrl)
        parcel.writeInt(puntos)
        parcel.writeInt(partidosJugados)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Equipo> {
        override fun createFromParcel(parcel: Parcel): Equipo {
            return Equipo(parcel)
        }

        override fun newArray(size: Int): Array<Equipo?> {
            return arrayOfNulls(size)
        }
    }
}
