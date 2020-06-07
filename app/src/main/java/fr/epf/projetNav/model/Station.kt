package fr.epf.projetNav.model
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "stations")
data class Station (@PrimaryKey(autoGenerate = true) val id: Int,
                    val name: String,
                    val slug: String,
                    val id_ligne: Int,
                    var liked: Boolean
) : Parcelable;



