package fr.epf.projetNav

import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.facebook.stetho.okhttp3.StethoInterceptor
import fr.epf.projetNav.data.AppDatabase
import fr.epf.projetNav.data.HoraireDao
import fr.epf.projetNav.data.LigneDao
import fr.epf.projetNav.data.StationDao
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory

fun AppCompatActivity.daoL() : LigneDao {
    val database =
        Room.databaseBuilder(this, AppDatabase::class.java, "projetNavs")
            .build()
    return database.getLigneDao()
}
fun AppCompatActivity.daoS() : StationDao {
    val database =
        Room.databaseBuilder(this, AppDatabase::class.java, "projetNavs")
            .build()
    return database.getStationDao()
}
fun AppCompatActivity.daoH() : HoraireDao {
    val database =
        Room.databaseBuilder(this, AppDatabase::class.java, "projetNavs")
            .build()
    return database.getHoraireDao()
}


fun AppCompatActivity.retrofit() : Retrofit {
    val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    val client = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .addNetworkInterceptor(StethoInterceptor())
        .build()

    return Retrofit.Builder()
        .baseUrl("https://api-ratp.pierre-grimaud.fr/v4/")
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .client(client)
        .build()

}


