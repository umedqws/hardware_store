package com.example.hardwarestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hardwarestore.databinding.FragmentBasketBinding


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

        val typeOrder: MutableList<String> = ArrayList()
        val list = listOf("Курером","Самовызов")
        for (i in list) {
            typeOrder.add(i)
            }
        val spinnerAdapter:ArrayAdapter<String> = ArrayAdapter(requireContext(), com.bumptech.glide.R.layout.support_simple_spinner_dropdown_item,typeOrder)
        binding.spiiner.adapter = spinnerAdapter
        binding.spiiner.onItemSelectedListener = object:OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                binding.tvTitle.text = list[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        binding.RecucleViewBasket.layoutManager = LinearLayoutManager(requireContext())

        basketAdapter = BasketAdapter()
        binding.RecucleViewBasket.adapter =basketAdapter
    }


    }
