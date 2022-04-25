package com.tstebner.mapsapp.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.tstebner.mapsapp.R
import com.tstebner.mapsapp.databinding.InputFragmentBinding

class InputFragment : Fragment() {

    val TOKEN = "pk.eyJ1IjoianN0ZWJuZXIiLCJhIjoiY2wyY3FlNzJxMGQ3czNqcm0ydmlqaGxmYyJ9.MXVjVMQqnVR2CqXe9lk9fg"
    val dim = 400
    var style = "satellite-v9"
    var lon = 0
    var lat = 0
    var zoom = 0
    var bear = 0
    var pitch = 0

    interface InputListener {
        fun onGetMap(url: String)
    }

    companion object {
        fun newInstance() = InputFragment()
    }

    private lateinit var binding: InputFragmentBinding
    private lateinit var viewModel: MainViewModel
    private val stylesArr = arrayListOf<String>("Satellite View", "Street View") // SPINNER TEXT HERE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = InputFragmentBinding.inflate(inflater, container, false)

        val arrayAdapter =
            activity?.let { ArrayAdapter(it, android.R.layout.simple_spinner_dropdown_item, stylesArr) }

        binding.spinnerStyle.adapter = arrayAdapter

        binding.spinnerStyle.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val select = p0?.getItemAtPosition(p2).toString()
                Log.d("Spinner",select)
                style = if (select == "Satellite View") {
                    "satellite-v9"
                } else {
                    "streets-v11"
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Log.d("SpinnerFragment","in onNothingSelected")
            }

        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

}