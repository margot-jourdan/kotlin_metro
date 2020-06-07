package fr.epf.projetNav.main

import android.Manifest
import android.app.SearchManager
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
import fr.epf.projetNav.R
import fr.epf.projetNav.service.ScanQRActivity
import fr.epf.projetNav.adapter.StationSearchAdapter

import fr.epf.projetNav.daoS
import fr.epf.projetNav.data.StationDao
import kotlinx.android.synthetic.main.activity_liste_stations_search.*
import kotlinx.coroutines.runBlocking

class SearchStationsResult : AppCompatActivity() {


    private var id: Int = 0
    private var code: String = ""
    private var stationDao: StationDao? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liste_stations)
        stationDao = daoS()
        id = intent.getIntExtra("id", 0)
        handleIntent(intent)

        stations_recyclerview.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

    }

    override fun onResume() {
        super.onResume()
        runBlocking {
            val slug = intent.getStringExtra("slug")
            val search = handleIntent(intent).toString()

            val stations = when (slug.isNullOrBlank()) {
                true -> {
                    stationDao?.getStationsfromName(search)
                }
                false -> {
                    stationDao?.getStationsfromSlug(slug)
                }
            }

            stations_recyclerview.adapter =
                StationSearchAdapter(stations ?: emptyList())
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

    private fun handleIntent(intent: Intent): String? {
        var search = ""
        if (Intent.ACTION_SEARCH == intent.action) {
            search = intent.getStringExtra(SearchManager.QUERY)!!
        }
        return search
    }


}
