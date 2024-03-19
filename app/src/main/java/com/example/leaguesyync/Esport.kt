package com.example.leaguesyync

import android.os.Parcel
import android.os.Parcelable

data class Esport(
    val id: Int,
    val nom: String,
    val numJugadors: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nom)
        parcel.writeInt(numJugadors)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Esport> {
        override fun createFromParcel(parcel: Parcel): Esport {
            return Esport(parcel)
        }

        override fun newArray(size: Int): Array<Esport?> {
            return arrayOfNulls(size)
        }
    }
}
