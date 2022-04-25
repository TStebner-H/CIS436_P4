package com.tstebner.mapsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import com.tstebner.mapsapp.ui.main.InputFragment
import com.tstebner.mapsapp.ui.main.MapFragment

class MainActivity : AppCompatActivity(), InputFragment.InputListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }

    override fun onGetMap(url: String) {
        val display = supportFragmentManager.findFragmentById((R.id.mapFrag)) as MapFragment
        display.updateDisplay(url)
    }
}