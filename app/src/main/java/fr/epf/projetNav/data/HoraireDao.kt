package fr.epf.projetNav.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import fr.epf.projetNav.model.Horaire

@Dao
interface HoraireDao {

    @Query("select * from horaires")
    suspend fun getAllHoraires() : List<Horaire>

    @Insert
    suspend fun addHoraire(horaire: Horaire)

    @Query("delete from horaires")
    suspend fun deleteAllHoraires()

    @Query("select code from lignes where id = :idLigne")
    suspend fun getcode(idLigne: Int) : String
}