package fr.epf.projetNav.main
import android.app.SearchManager;
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import fr.epf.projetNav.*
import fr.epf.projetNav.adapter.LigneAdapter
import fr.epf.projetNav.adapter.StationSearchAdapter
import fr.epf.projetNav.data.LigneDao
import fr.epf.projetNav.data.StationDao
import fr.epf.projetNav.model.Ligne
import fr.epf.projetNav.model.Station
import fr.epf.projetNav.service.APIRATPservice
import fr.epf.projetNav.service.ScanQRActivity
import kotlinx.android.synthetic.main.home_liste_lignes_activity.*
import kotlinx.android.synthetic.main.home_liste_lignes_activity.stations_recyclerview

import kotlinx.coroutines.runBlocking



class ListLigneActivity : AppCompatActivity() {



    private var ligneDao: LigneDao? = null
    private var stationDao: StationDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_liste_lignes_activity)
        lignes_recyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        stations_recyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        ligneDao = daoL()
        stationDao = daoS()


    }


    override fun onResume() {
        super.onResume()
        runBlocking {
            val lignes = ligneDao?.getLignes()
            lignes_recyclerview.adapter =
                LigneAdapter(lignes ?: emptyList())
            when (lignes.isNullOrEmpty()) {
                /*
On teste si la BDD existe ou pas
*/
                true -> {
                    bddLignesStation() //on crée la BDD
                }

                false -> {
                    val stations  = stationDao?.getLikedStations()
                    stations_recyclerview.adapter =
                        StationSearchAdapter(stations ?: emptyList())
                }
            }
        }
    }


    /*
     Import du menu et barre de recherche
     */

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.defaultmenu, menu)
        val searchView =menu.findItem(R.id.search).actionView as SearchView
        searchView.queryHint="Rechercher un nom de station"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(s: String): Boolean {
                return false
            }

            override fun onQueryTextSubmit(search: String): Boolean {
                val intent = Intent(applicationContext, SearchStationsResult::class.java)
                Log.d("ici", search)
                intent.putExtra(SearchManager.QUERY, search)
                intent.action = Intent.ACTION_SEARCH
                startActivity(intent)
                return true
            }
        })
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean =
        when (item.itemId) {
            R.id.action_scan -> {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                    == PackageManager.PERMISSION_DENIED){
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 1);
                }else{
                    val intent = Intent(this, ScanQRActivity::class.java)
                    startActivity(intent)
                }

                true
            }
            else -> onOptionsItemSelected(item)
        }

    /*
Création et remplissage des tables lignes et stations
 */

    private fun bddLignesStation() {

        val service = retrofit().create(APIRATPservice::class.java)
        runBlocking {
            val result = service.getLignes()
            result.result.metros.map {

                val ligne = Ligne(it.id, it.code, it.name, it.directions)
                ligneDao?.addLigne(ligne)
                val idLigne = it.id
                val allstations = service.getStations(it.code)
                allstations.result.stations.map {
                    val station = Station(0, it.name, it.slug, idLigne, false)
                    stationDao?.addStation(station)
                }
            }
            val lignes = ligneDao?.getLignes()
            lignes_recyclerview.adapter =
                LigneAdapter(lignes ?: emptyList())
        }

    }


}
