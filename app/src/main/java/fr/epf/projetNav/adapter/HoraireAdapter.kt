package fr.epf.projetNav.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.epf.projetNav.R
import fr.epf.projetNav.model.Horaire
import kotlinx.android.synthetic.main.horaire_view.view.*



class HoraireAdapter(private val horaires: List<Horaire>) : RecyclerView.Adapter<HoraireAdapter.HoraireViewHolder>() {

    class HoraireViewHolder(val horaireView : View) : RecyclerView.ViewHolder(horaireView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoraireViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.horaire_view, parent, false)
        return HoraireViewHolder(view)
    }

    override fun getItemCount(): Int  = horaires.size


    override fun onBindViewHolder(holder: HoraireViewHolder, position: Int) {
        val horaire = horaires[position]
        holder.horaireView.horaire_direction.text = horaire.destination
        holder.horaireView.horaire_temps.text = horaire.message
    }

}
