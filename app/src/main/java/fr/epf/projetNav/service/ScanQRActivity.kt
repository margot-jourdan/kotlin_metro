package fr.epf.projetNav.service

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import me.dm7.barcodescanner.zxing.ZXingScannerView
import com.google.zxing.Result
import fr.epf.projetNav.data.StationDao
import fr.epf.projetNav.main.SearchStationsResult


class ScanQRActivity : Activity(), ZXingScannerView.ResultHandler {
    private var stationDao: StationDao? = null
    private var mScannerView: ZXingScannerView? = null
    public override fun onCreate(state: Bundle?) {
        super.onCreate(state)
        mScannerView = ZXingScannerView(this) // Programmatically initialize the scanner view
        setContentView(mScannerView) // Set the scanner view as the content view
    }

    public override fun onResume() {
        super.onResume()
        mScannerView!!.setResultHandler(this) // Register ourselves as a handler for scan results.
        mScannerView!!.startCamera() // Start camera on resume
    }

    public override fun onPause() {
        super.onPause()
        mScannerView!!.stopCamera() // Stop camera on pause
    }

    override fun handleResult(rawResult: Result?) { //Avec le résultat on ouvre la page résultat
        val slugStation = rawResult.toString()
        Log.d("Veleur du scan", slugStation)
        intent = Intent(this, SearchStationsResult::class.java)
        intent.putExtra("slug", slugStation)
        startActivity(intent)
    }
}