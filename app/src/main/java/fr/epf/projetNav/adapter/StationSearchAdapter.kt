package fr.epf.projetNav.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.epf.projetNav.R
import fr.epf.projetNav.main.HorairesActivity
import fr.epf.projetNav.model.Station
import kotlinx.android.synthetic.main.station_view.view.*


class StationSearchAdapter(private val stations: List<Station>) :
    RecyclerView.Adapter<StationSearchAdapter.StationViewHolder>() {

    class StationViewHolder(val stationView: View) : RecyclerView.ViewHolder(stationView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationViewHolder {

        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.station_view, parent, false)
        return StationViewHolder(view)
    }

    override fun getItemCount(): Int = stations.size


    override fun onBindViewHolder(holder: StationViewHolder, position: Int) {
        val station = stations[position]

        holder.stationView.station_name_textview.text = station.name

        holder.stationView.fav_station_imageview.maxWidth = 20
        holder.stationView.fav_station_imageview.maxHeight = 20
        holder.stationView.fav_station_imageview.setImageResource(
            when (station.id_ligne) {
                 62-> R.drawable.m1
                67-> R.drawable.m2
                68-> R.drawable.m3
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
                74-> R.drawable.m7b

                else -> R.drawable.mdefault
            }

        )


        holder.stationView.setOnClickListener {

            val intent = Intent(it.context, HorairesActivity::class.java)
            intent.putExtra("slug", station.slug)
            intent.putExtra("id_station", station.id)
            intent.putExtra("id", station.id_ligne)

            it.context.startActivity(intent)
        }
    }

}
