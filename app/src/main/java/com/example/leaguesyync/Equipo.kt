package com.example.leaguesyync

import android.os.Parcel
import android.os.Parcelable

data class Equipo(
    val id: Int,
    val nom: String,
    val lliga: Lliga,
    val codiEquip: String,
    val mascota: String,
    val color1: String,
    val color2: String,
    val escut: String,
    val partitsGuanyats: Int,
    val partitsEmpatats: Int,
    val partitsPerduts: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readParcelable(Lliga::class.java.classLoader) ?: Lliga(0, "", Esport(0, "", 0)),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nom)
        parcel.writeParcelable(lliga, flags)
        parcel.writeString(codiEquip)
        parcel.writeString(mascota)
        parcel.writeString(color1)
        parcel.writeString(color2)
        parcel.writeString(escut)
        parcel.writeInt(partitsGuanyats)
        parcel.writeInt(partitsEmpatats)
        parcel.writeInt(partitsPerduts)
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
