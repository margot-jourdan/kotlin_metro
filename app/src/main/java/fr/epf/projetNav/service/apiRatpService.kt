package fr.epf.projetNav.service

import retrofit2.http.GET
import retrofit2.http.Path

/*
Définition des requêtes pour l'API
*/

interface APIRATPservice {

    @GET("lines/metros")
    suspend fun getLignes() : GetLinesResult

    @GET("stations/metros/{code}")
    suspend fun getStations(
        @Path("code") code:String
    ): GetStationsResults

    @GET("schedules/metros/{code}/{slug}/A%2BR")
    suspend fun getHoraires(
        @Path("code") code:String,
        @Path("slug") slug:String
    ): GetHorairesResults

}

/*
Traitement du JSON
*/
data class Metro(val code: String = "", val name: String = "",val directions: String = "",val id: Int = 0)
data class Metros(val metros:List<Metro> = emptyList())
data class GetLinesResult(val result: Metros = Metros())

data class Station(val name:String="",val slug:String="")
data class Stations(val stations:List<Station> = emptyList())
data class GetStationsResults(val result: Stations = Stations())

data class Horaire(val message:String="",val destination:String="")
data class Horaires(val schedules:List<Horaire> = emptyList())
data class GetHorairesResults(val result: Horaires = Horaires())


