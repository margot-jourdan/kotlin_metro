package fr.epf.projetNav.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

    @Parcelize
    @Entity(tableName = "horaires")
    data class Horaire (@PrimaryKey(autoGenerate = true) val id_index: Int,
                        val message: String,
                        val destination: String
    )
        : Parcelable;

