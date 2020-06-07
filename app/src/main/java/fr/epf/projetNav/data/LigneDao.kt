package fr.epf.projetNav.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import fr.epf.projetNav.model.Ligne

@Dao
interface LigneDao {

    @Query("select * from lignes order by code")
    suspend fun getLignes() : List<Ligne>

    @Insert
    suspend fun addLigne(ligne: Ligne)

    @Delete
    suspend fun deleteLigne(ligne: Ligne)

    @Query("select * from lignes where id = :idLigne")
    suspend fun getLigne(idLigne: Int) : Ligne


}