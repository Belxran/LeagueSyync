package com.example.leaguesyync

import android.os.Parcel
import android.os.Parcelable

data class Lliga(
    val id: Int,
    val nom: String,
    val esport: Esport
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readParcelable<Esport>(Esport::class.java.classLoader) ?: Esport(0, "", 0)
    )
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(nom)
        parcel.writeParcelable(esport, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Lliga> {
        override fun createFromParcel(parcel: Parcel): Lliga {
            return Lliga(parcel)
        }

        override fun newArray(size: Int): Array<Lliga?> {
            return arrayOfNulls(size)
        }
    }
}