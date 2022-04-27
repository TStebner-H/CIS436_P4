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
        submitClicked()
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
     *
     * I removed onGetMap() since just doing everything in the submitClicked function makes
     * everything less messy, plus the name was probably a bad choice on my part lol.
     * I also moved values like dimension and token into the viewmodel to make it easier to change
     * later if needed.
     * Thanks for all your help man, I completely neglected the viewmodel when first working on
     * this app but honestly having the viewmodel makes things a lot easier.
     * -Jordan
    */
    override fun submitClicked(){
        val style = viewModel.getStyle()
        val lon = viewModel.getLon()
        val lat = viewModel.getLat()
        val zoom = viewModel.getZoom()
        val bear = viewModel.getBear()
        val pitch = viewModel.getPitch()
        val dim = viewModel.getDim()
        val token = viewModel.getToken()

        val url = "https://api.mapbox.com/styles/v1/mapbox/$style/static/$lon,$lat,$zoom,$bear,$pitch/${dim}x$dim?access_token=$token"

        // creates a small popup to display the data, we can keep/remove this if wanted
        Toast.makeText(this, "Style: $style, Lon: $lon, Lat: $lat\n" +
                "Zoom: $zoom, Bear: $bear, Pitch: $pitch", Toast.LENGTH_LONG).show()

        val display = supportFragmentManager.findFragmentById((R.id.mapFrag)) as MapFragment
        display.updateDisplay(url, dim)

    }
}