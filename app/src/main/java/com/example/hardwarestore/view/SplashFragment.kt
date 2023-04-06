package com.example.hardwarestore.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.hardwarestore.R
import com.example.hardwarestore.databinding.FragmentSplashBinding
import com.example.hardwarestore.viewmodel.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SplashFragment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    private val binding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSplashBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("CommitPrefEdits")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val activityViewModel = ViewModelProvider(requireActivity())[ActivityViewModel::class.java]
        val userViewModel = ViewModelProvider(this)[RegistrationViewModel::class.java]
        val userViewModels = ViewModelProvider(this)[BasketViewModel::class.java]
        val userTimeViewModel = ViewModelProvider(this)[UserTimeViewModel::class.java]






        lifecycleScope.launch {
            delay(1000)
            userTimeViewModel.getUserTime().observe(viewLifecycleOwner){
                if (it.isEmpty()){
                    findNavController().navigate(R.id.action_splashFragment2_to_loginFragment2)
                }else{
                    for (i in it){
                      userViewModel.userById(i.userId).observe(viewLifecycleOwner){
                        val intent = Intent(requireContext(),MainActivity::class.java)
                        Log.v("userstest","${it.id}")

                        intent.putExtra("title",it)
                        startActivity(intent)
                    }
                    }
                }
            }

        }
        }
        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
}


