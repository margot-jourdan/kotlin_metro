package fr.epf.projetNav.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import fr.epf.projetNav.R
import fr.epf.projetNav.adapter.HoraireAdapter
import fr.epf.projetNav.daoH
import fr.epf.projetNav.daoS
import fr.epf.projetNav.data.HoraireDao
import fr.epf.projetNav.data.StationDao
import fr.epf.projetNav.model.Horaire
import fr.epf.projetNav.model.Station
import fr.epf.projetNav.retrofit
import fr.epf.projetNav.service.APIRATPservice
import kotlinx.android.synthetic.main.activity_horaires.*
import kotlinx.coroutines.runBlocking


class HorairesActivity : AppCompatActivity() {
    private var horaireDao: HoraireDao? = null
    private var stationDao: StationDao? = null
    private var station: Station? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horaires)

        horaires_recyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        horaireDao = daoH()
        stationDao = daoS()

        horaire_like_station.setOnClickListener {

            when (station!!.liked) {
                false -> {
                    runBlocking {
                        stationDao?.likeStation(intent.getIntExtra("id_station", 0))
                    }

                }
                true -> {
                    runBlocking {
                        stationDao?.dislikeStation(intent.getIntExtra("id_station", 0))
                    }
                }

            }
            refreshHoraire()
        }
        refreshHoraire()

    }

    override fun onResume() {
        super.onResume()
        runBlocking {
            station = stationDao?.getStationfromStation(intent.getIntExtra("id_station", 0))
            horaire_nom_ligne_textview.text = station?.name
            val horaires = horaireDao?.getAllHoraires()
            horaires_recyclerview.adapter =
                HoraireAdapter(horaires ?: emptyList())
        }
refreshHoraire()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.horaires, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.action_actualiser -> {
                refreshHoraire()

            }

            else -> {
                onOptionsItemSelected(item)

            }
        }
        return false
    }

    private fun refreshHoraire() {


        var idLigne = intent.getIntExtra("id", 0)
        var slugStation = intent.getStringExtra("slug")


        val service = retrofit().create(APIRATPservice::class.java)
        runBlocking {
            horaireDao?.deleteAllHoraires()
            var codeLigne = horaireDao?.getcode(idLigne)
            val result = service.getHoraires(codeLigne!!, slugStation!!)

            result.result.schedules.map {
                val horaire = Horaire(0, it.message, it.destination)
                horaireDao?.addHoraire(horaire)
            }
            val horaires = horaireDao?.getAllHoraires()
            horaires_recyclerview.adapter =
                HoraireAdapter(horaires ?: emptyList())
        }
        seticons()
    }

    private fun seticons() {
        runBlocking {
            station = stationDao?.getStationfromStation(intent.getIntExtra("id_station", 0))
            horaire_nom_ligne_textview.text = station?.name
            horaire_like_station.setImageResource(

                when (station!!.liked) {
                    false -> R.drawable.not_favorited
                    true -> R.drawable.favorited
                }

            )

            horaire_ligne_imageview.setImageResource(
                when (station?.id_ligne) {
                    62 -> R.drawable.m1
                    67 -> R.drawable.m2
                    68 -> R.drawable.m3
                    70 -> R.drawable.m4
                    71 -> R.drawable.m5
                    72 -> R.drawable.m6
                    73 -> R.drawable.m7
                    172562 -> R.drawable.m8
                    76 -> R.drawable.m9
                    63 -> R.drawable.m10
                    64 -> R.drawable.m11
                    65 -> R.drawable.m12
                    66 -> R.drawable.m13
                    55098 -> R.drawable.m14
                    69 -> R.drawable.m3b
                    74 -> R.drawable.m7b

                    else -> R.drawable.mdefault
                }

            )

        }
    }

}