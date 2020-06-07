package fr.epf.projetNav.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import fr.epf.projetNav.model.Station

@Dao
interface StationDao {

    @Insert
    suspend fun addStation(station: Station)

    @Delete
    suspend fun deleteStation(station: Station)

    @Query("select * from stations where id = :idStation")
    suspend fun getStationfromStation(idStation: Int) : Station

    @Query("select * from stations where id_ligne = :idLigne")
    suspend fun getStationfromLigne(idLigne: Int) : List<Station>

    @Query("select * from stations where liked = 1")
    suspend fun getLikedStations() : List<Station>

    @Query("update stations set liked = 1 where id = :idStation")
    suspend fun likeStation(idStation: Int)
    @Query("update stations set liked = 0 where id = :idStation")
    suspend fun dislikeStation(idStation: Int)

    @Query("select * from stations where slug = :slug")
    suspend fun getStationsfromSlug(slug: String) : List<Station>

    @Query("select * from stations where name like '%' || :name || '%'")
    suspend fun getStationsfromName(name: String) : List<Station>

    @Query("select code from lignes where id = :idLigne")
    suspend fun getcode(idLigne: Int) : String

}