package fr.epf.projetNav.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.epf.projetNav.R
import fr.epf.projetNav.main.DetailLigneActivity
import fr.epf.projetNav.main.HorairesActivity
import fr.epf.projetNav.model.Station
import kotlinx.android.synthetic.main.station_view.view.*


class StationAdapter(private val stations: List<Station>) :
    RecyclerView.Adapter<StationAdapter.StationViewHolder>() {

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

        holder.stationView.fav_station_imageview.setImageResource(

            when (station.liked) {
                false -> R.drawable.not_favorited
                true -> R.drawable.favorited
            }

        )
        holder.stationView.fav_station_imageview.setOnClickListener {


            var etatLike = when (station.liked) {
                false -> {
                    "DTL" //dislike to like
                }
                true -> {
                    "LTD"
                }
            }
            val intent = Intent(it.context, DetailLigneActivity::class.java)
            intent.putExtra("etatLike", etatLike)
            intent.putExtra("id", station.id_ligne)
            intent.putExtra("idStation", station.id)


            it.context.startActivity(intent)
        }


        holder.stationView.setOnClickListener {
            val intent = Intent(it.context, HorairesActivity::class.java)
            intent.putExtra("slug", station.slug)
            intent.putExtra("id", station.id_ligne)
            intent.putExtra("id_station", station.id)

            it.context.startActivity(intent)
        }
    }

}
