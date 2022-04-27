package com.tstebner.mapsapp.ui.main

import android.graphics.Bitmap
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.Volley
import com.tstebner.mapsapp.R
import com.tstebner.mapsapp.databinding.MapFragmentBinding

class MapFragment : Fragment() {

    companion object {
        fun newInstance() = MapFragment()
    }

    private lateinit var binding: MapFragmentBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MapFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }

    fun updateDisplay(url: String, dim: Int) {
        val queue = Volley.newRequestQueue(activity?.applicationContext)
        val imageRequest = ImageRequest (
            url,
            {bitmap ->
                binding.imgMap.setImageBitmap(bitmap)
            },
            dim,
            dim,
            ImageView.ScaleType.FIT_START,
            Bitmap.Config.ARGB_8888,
            { error ->
                Log.d("MapFragment",error.message.toString())
            }
        )
        queue.add(imageRequest)
    }

}