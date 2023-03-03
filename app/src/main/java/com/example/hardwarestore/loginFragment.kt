package com.example.hardwarestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hardwarestore.databinding.FragmentLoginBinding
import com.example.hardwarestore.viewmodel.RegistrationViewModel

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val registrationViewModel = ViewModelProvider(this)[RegistrationViewModel::class.java]
        registrationViewModel.getUser.observe(viewLifecycleOwner){

        }

        binding.registration.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_loginFragment_to_registrationFragment)
        }
    }

}