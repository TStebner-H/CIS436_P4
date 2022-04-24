package com.tstebner.mapsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tstebner.mapsapp.ui.main.InputFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, InputFragment.newInstance())
                .commitNow()
        }
    }
}