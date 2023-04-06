package com.example.hardwarestore.view

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hardwarestore.databinding.FragmentLoginBinding
import com.example.hardwarestore.databinding.FragmentRegistrationBinding
import com.example.hardwarestore.viewmodel.RegistrationViewModel


class RegistrationFragment : Fragment() {
  private var _binding:FragmentRegistrationBinding? = null
    private val binding
    get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRegistrationBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val userViewModel = ViewModelProvider(this)[RegistrationViewModel::class.java]

        binding.back.setOnClickListener {
            val navController = findNavController()
            navController.popBackStack()
        }
        binding.regist.setOnClickListener {

            userViewModel.insertNewUser(
                binding.firstNameUser.text.toString(),
            binding.lastNameUser.text.toString(),
            binding.nickNameUser.text.toString(),
            binding.passwordUser.text.toString(),
            binding.numberTelefonUser.text.toString())


            userViewModel.getUsers(
                binding.passwordUser.text.toString(),
                binding.numberTelefonUser.text.toString()
            ).observe(viewLifecycleOwner) {

                val intent = Intent(requireContext(), MainActivity::class.java)
                intent.putExtra(
                    "title",it
                )
                startActivity(intent)
            }

        }
    }

}