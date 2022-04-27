package com.tstebner.mapsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.tstebner.mapsapp.ui.main.InputFragment
import com.tstebner.mapsapp.ui.main.MainViewModel
import com.tstebner.mapsapp.ui.main.MapFragment

class MainActivity : AppCompatActivity(), InputFragment.InputListener {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    // check the green comments below please -Galen
    override fun onGetMap(url: String) {
        val display = supportFragmentManager.findFragmentById((R.id.mapFrag)) as MapFragment
        display.updateDisplay(url)
    }

    // pull the data from the viewModel and send to mapFragment
    /**
     * I don't know how the API works, but it seems like you're expecting to get a URL
     * and send it to the mapFragment for display. If that's the case, then you should
     * get the URL in this function, then call onGetMap().
     *
     * If you need to get the variable data (style, lat, lon, ...) in the mapFragment
     * for some reason, call the getter functions like below. viewModel is already instantiated
     * in MapFragment, so you're all good to just use the get() functions.
     * -Galen
    */
    override fun submitClicked(){
        val style = viewModel.getStyle()
        val lon = viewModel.getLon()
        val lat = viewModel.getLat()
        val zoom = viewModel.getZoom()
        val bear = viewModel.getBear()
        val pitch = viewModel.getPitch()

        // creates a small popup to display the data, we can keep/remove this if wanted
        Toast.makeText(this, "Style: $style, Lon: $lon, Lat: $lat\n" +
                "Zoom: $zoom, Bear: $bear, Pitch: $pitch", Toast.LENGTH_LONG).show()
    }
}