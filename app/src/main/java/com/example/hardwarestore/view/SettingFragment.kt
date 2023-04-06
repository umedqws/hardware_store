package com.example.hardwarestore.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hardwarestore.databinding.FragmentSetteingBinding
import com.example.hardwarestore.viewmodel.ActivityViewModel


class SettingFragment : Fragment() {
    private var _binding: FragmentSetteingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSetteingBinding.inflate(inflater,container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        val userViewModel = ViewModelProvider(requireActivity())[ActivityViewModel::class.java]

        binding.nameUserInput.setText(binding.nameUserInput.text.toString() + userViewModel.user!!.firstName + " " + userViewModel.user!!.lastname)
        binding.numberPhoneUserInput.setText(binding.numberPhoneUserInput.text.toString() + userViewModel.user!!.numberPhone)


    }

}