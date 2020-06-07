package fr.epf.projetNav

import android.app.Application
import com.facebook.stetho.Stetho

class LigneApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}