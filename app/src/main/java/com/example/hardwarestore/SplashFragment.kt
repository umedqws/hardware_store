package com.example.hardwarestore

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hardwarestore.databinding.FragmentSplashBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class SplashFragment : Fragment() {
     private val _binding: FragmentSplashBinding? = null
        var binding = _binding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSplashBinding.inflate(inflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Handler().postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
        },3000)

    }
}