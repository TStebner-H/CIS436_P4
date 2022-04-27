package com.tstebner.mapsapp.ui.main

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Half.toFloat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.tstebner.mapsapp.R
import com.tstebner.mapsapp.databinding.InputFragmentBinding
import java.lang.ClassCastException

class InputFragment : Fragment() {

    var style = "satellite-v9"
    var zoom = 0
    var bear = 0
    var pitch = 0

    interface InputListener {
        fun submitClicked()
    }

    companion object {
        fun newInstance() = InputFragment()
    }

    private lateinit var binding: InputFragmentBinding
    private lateinit var viewModel: MainViewModel
    private var activityCallback : InputListener? = null

    private val stylesArr = arrayListOf<String>("Satellite View", "Street View") // SPINNER TEXT HERE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
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

        binding.btnSubmit.setOnClickListener { submitClicked() }

        // set the seekbarChangeListeners
        binding.zoomBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
                    zoom = progress
                    binding.zoomBarValue.text = zoom.toString()
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {

                }
                override fun onStopTrackingTouch(p0: SeekBar?) {

                }
            }
        )

        binding.pitchBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
                    pitch = progress
                    binding.pitchBarValue.text = pitch.toString()
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {

                }
                override fun onStopTrackingTouch(p0: SeekBar?) {

                }
            }
        )

        binding.bearingBar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
                    bear = progress
                    binding.bearingBarValue.text = bear.toString()
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {

                }
                override fun onStopTrackingTouch(p0: SeekBar?) {

                }
            }
        )


        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            activityCallback = context as InputListener
        }catch (e : ClassCastException) {
            throw ClassCastException("$context must implement TopFragmentListener")
        }
    }

    // save all the data and send to the viewModel
    private fun submitClicked() {
        var lonVal: Float = 0f
        var latVal: Float = 0f

        viewModel.setStyle(style)

        // if the either inputText is empty, convert to 0.0f
        try {
            lonVal = binding.longText.text.toString().toFloat()
        }
        catch (e: Exception) {
            Log.e("String.toFloat()", "Error casting lonVal to a float")
        }
        try {
            latVal = binding.latText.text.toString().toFloat()
        }
        catch (e: Exception) {
            Log.e("String.toFloat()", "Error casting latVal to a float")
        }

        viewModel.setLon(lonVal)
        viewModel.setLat(latVal)
        viewModel.setZoom(zoom)
        viewModel.setBear(bear)
        viewModel.setPitch(pitch)

        activityCallback?.submitClicked()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}
