package fr.epf.projetNav.data

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.epf.projetNav.model.Horaire
import fr.epf.projetNav.model.Ligne
import fr.epf.projetNav.model.Station

@Database(entities = [Ligne::class, Station::class, Horaire::class], version = 1)


abstract class AppDatabase : RoomDatabase() {
    abstract fun getLigneDao() : LigneDao
    abstract fun getStationDao() : StationDao
    abstract fun getHoraireDao() : HoraireDao
}


