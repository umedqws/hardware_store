package com.example.hardwarestore.view

import android.os.Bundle
import com.yandex.mapkit.map.MapObjectCollection
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.ContextCompat
import androidx.core.view.isInvisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hardwarestore.R
import com.example.hardwarestore.databinding.FragmentLocationBinding
import com.google.android.gms.maps.model.LatLng
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.render.internal.Size
import com.yandex.runtime.image.ImageProvider
import com.yandex.runtime.ui_view.ViewProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class LocationFragment : Fragment() {
  private var _binding: FragmentLocationBinding? = null
    val binding get() = _binding!!
    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.setApiKey("5d11ee0d-c417-4a07-a0a9-534f91a420de")

        lifecycleScope.launch {
            delay(2000)
            MapKitFactory.initialize(requireContext())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        mapView = binding.mapview
        val point = Point(38.579493, 68.790521)
        val viewM = View(requireContext()).apply {
            setBackgroundResource(R.drawable.markers)
        }
        mapView.map.mapObjects.addPlacemark(point, ViewProvider(viewM))
        mapView.map.move(CameraPosition(point, 18f,0f,0f), Animation(Animation.Type.SMOOTH, 1f), null)



        binding.pickup.setOnClickListener {
            binding.delivery.setBackgroundResource(R.drawable.no_seelected)
            binding.mapview.visibility = VISIBLE
            binding.bottomButton.visibility = GONE
            binding.pickup.setTextColor(ContextCompat.getColor(requireActivity(), R.color.white))
            binding.delivery.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))
            binding.pickup.setBackgroundResource(R.drawable.button_background)
        }

        binding.delivery.setOnClickListener {
            binding.bottomButton.visibility = VISIBLE
           binding.delivery.setBackgroundResource(R.drawable.button_background)
            binding.pickup.setBackgroundResource(R.drawable.no_seelected)
            binding.mapview.visibility = GONE
            binding.pickup.setTextColor(ContextCompat.getColor(requireActivity(), R.color.black))
            binding.delivery.setTextColor(ContextCompat.getColor(requireActivity(), R.color.white))
        }

    }

    override fun onStart() {
        mapView.onStart()
        MapKitFactory.getInstance().onStart()
        super.onStart()
    }
    override fun onStop() {
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
        super.onStop()
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
    }


}