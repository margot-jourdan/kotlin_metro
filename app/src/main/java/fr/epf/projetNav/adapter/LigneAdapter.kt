package fr.epf.projetNav.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import fr.epf.projetNav.main.DetailLigneActivity
import fr.epf.projetNav.R
import fr.epf.projetNav.model.Ligne
import kotlinx.android.synthetic.main.ligne_view.view.*

class LigneAdapter(private val lignes: List<Ligne>) : RecyclerView.Adapter<LigneAdapter.LigneViewHolder>() {

    class LigneViewHolder(val ligneView : View) : RecyclerView.ViewHolder(ligneView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LigneViewHolder {
        val layoutInfater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInfater.inflate(R.layout.ligne_view, parent, false)
        return LigneViewHolder(view)
    }

    override fun getItemCount(): Int  = lignes.size


    override fun onBindViewHolder(holder: LigneViewHolder, position: Int) {

        val ligne = lignes[position]
        holder.ligneView.ligne_name_textview.text = ligne.name + " " + ligne.directions

        holder.ligneView.ligne_imageview.setImageResource(
            when(ligne.code){
                "1" -> R.drawable.m1
                "2" -> R.drawable.m2
                "3" -> R.drawable.m3
                "4" -> R.drawable.m4
                "5" -> R.drawable.m5
                "6" -> R.drawable.m6
                "7" -> R.drawable.m7
                "8" -> R.drawable.m8
                "9" -> R.drawable.m9
                "10" -> R.drawable.m10
                "11" -> R.drawable.m11
                "12" -> R.drawable.m12
                "13" -> R.drawable.m13
                "14" -> R.drawable.m14
                "3b" -> R.drawable.m3b
                "7b" -> R.drawable.m7b

                else -> R.drawable.mdefault
            }

        )
/*
Cliquer sur une ligne renvoie les stations correspondantes
*/
        holder.ligneView.setOnClickListener {
            val intent = Intent(it.context, DetailLigneActivity::class.java)
            intent.putExtra("id", ligne.id)
            it.context.startActivity(intent)
        }
    }

}
