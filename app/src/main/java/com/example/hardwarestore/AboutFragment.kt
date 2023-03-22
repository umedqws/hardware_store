package com.example.hardwarestore

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hardwarestore.databinding.FragmentAboutBinding
import com.example.hardwarestore.databinding.FragmentStoreBinding
import com.example.hardwarestore.model.UserDao
import com.example.hardwarestore.viewmodel.BasketViewModel
import com.example.hardwarestore.viewmodel.RegistrationViewModel

class AboutFragment : Fragment() {
    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAboutBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val basketViewModel = ViewModelProvider(this)[BasketViewModel::class.java]
        val userViewModel = ViewModelProvider(this)[RegistrationViewModel::class.java]

        val args:AboutFragmentArgs by navArgs()
        binding.imageAbout.setImageResource(args.products.image!!)
        binding.name.text = args.products.nameProduct
        binding.price.text = "${args.products.price} sm"
        binding.about.text = args.products.aboutProduct
        binding.backKatalog.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.inBasket.setOnClickListener {
            basketViewModel.insert(
                args.products.nameProduct!!,
                args.products.aboutProduct!!,
                args.products.price!!,
                args.products.aboutProduct!!,
                args.products.categoryId!!,
                args.products.image!!,
                1
            )
        }
    }


}
