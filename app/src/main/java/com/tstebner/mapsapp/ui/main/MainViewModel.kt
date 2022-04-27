package com.tstebner.mapsapp.ui.main

import androidx.lifecycle.ViewModel
import android.util.Log

class MainViewModel : ViewModel() {
    // data to be used in the mapFragment
    private var style: String = ""
    private var lon: Float = 0f
    private var lat: Float = 0f
    private var zoom: Int = 10
    private var bear: Int = 0
    private var pitch: Int = 0

    // use these to retrieve data
    fun setStyle(style: String) { this.style = style }
    fun getStyle(): String { return style }

    fun setLon(lon: Float) { this.lon = lon }
    fun getLon(): Float { return lon }

    fun setLat(lat: Float) { this.lat = lat }
    fun getLat(): Float {return lat}

    fun setZoom(zoom: Int) { this.zoom = zoom }
    fun getZoom(): Int { return zoom }

    fun setBear(bear: Int) { this.bear = bear }
    fun getBear(): Int { return bear }

    fun setPitch(pitch: Int) { this.pitch = pitch }
    fun getPitch(): Int { return pitch }
}
