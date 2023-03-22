package com.example.hardwarestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hardwarestore.databinding.FragmentLoginBinding
import com.example.hardwarestore.model.Users
import com.example.hardwarestore.viewmodel.RegistrationViewModel
import kotlinx.coroutines.Job

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
        val userViewModel = ViewModelProvider(this)[RegistrationViewModel::class.java]
            userViewModel.getAllUser().observe(viewLifecycleOwner){
                val user = it
            }

        binding.logoText.setOnClickListener {
            userViewModel.insertNewUser("user","user","user","111","111")
        }



        binding.registration.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_loginFragment_to_registrationFragment)
        }
        binding.login.setOnClickListener {
            if ((binding.numberLogin.text.toString() == "") && (binding.passwordLogin.text.toString() == "")){
                Toast.makeText(
                    binding.root.context,
                    "Неправльный пароль или номер телефона",
                    Toast.LENGTH_SHORT
                ).show()
            }else {
                userViewModel.getUsers(
                    binding.passwordLogin.text.toString(),
                    binding.numberLogin.text.toString()
                ).observe(viewLifecycleOwner) {
                    if ((it.numberPhone == null) && (it.password == null)) {
                        Toast.makeText(
                            binding.root.context,
                            "Неправльный пароль или номер телефона",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        if (binding.numberLogin.text.toString() == it.numberPhone && binding.passwordLogin.text.toString() == it.password) {
                            val navController = findNavController()
                            navController.navigate(R.id.action_loginFragment_to_storeFragment)
                        } else {
                            Toast.makeText(
                                binding.root.context,
                                "Неправльный пароль или номер телефона",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }

            }
        }

    }
}




