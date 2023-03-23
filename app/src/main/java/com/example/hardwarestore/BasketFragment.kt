package com.example.hardwarestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hardwarestore.databinding.FragmentBasketBinding
import com.example.hardwarestore.viewmodel.ActivityViewModel
import com.example.hardwarestore.viewmodel.BasketViewModel
import com.example.hardwarestore.viewmodel.ProductsViewModel
import com.example.hardwarestore.viewmodel.RegistrationViewModel


class BasketFragment : Fragment() {
    private var _binding: FragmentBasketBinding? = null
    private val binding get() = _binding!!
    lateinit var basketAdapter: BasketAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBasketBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val  basketViewModel = ViewModelProvider(this)[BasketViewModel::class.java]

        val typeOrder: MutableList<String> = ArrayList()
        val list = listOf("Курером","Самовызов")
        for (i in list) {
            typeOrder.add(i)
            }
        val spinnerAdapter:ArrayAdapter<String> = ArrayAdapter(requireContext(),
            R.layout.spinner,typeOrder)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spiiner.adapter = spinnerAdapter
        binding.spiiner.onItemSelectedListener = object:OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        binding.RecucleViewBasket.layoutManager = LinearLayoutManager(requireContext())

        val activityViewModel = ViewModelProvider(requireActivity())[ActivityViewModel::class.java]
        val productViewModel = ViewModelProvider(this)[ProductsViewModel::class.java]

        basketAdapter = BasketAdapter()
        binding.RecucleViewBasket.adapter =basketAdapter

        basketViewModel.getBasketProduct(activityViewModel.user.id).productId?.let {
            productViewModel.getProductsForBasket(it)?.observe(viewLifecycleOwner){
                basketAdapter.submitList(it)
            }
        }
    }


    }
