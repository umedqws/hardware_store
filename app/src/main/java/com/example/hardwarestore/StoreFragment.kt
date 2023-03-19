package com.example.hardwarestore

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hardwarestore.databinding.FragmentStoreBinding
import com.example.hardwarestore.model.Products
import com.example.hardwarestore.viewmodel.CategoryViewModel
import com.example.hardwarestore.viewmodel.ProductsViewModel

class StoreFragment : Fragment() {
    private var _binding: FragmentStoreBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentStoreBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter: HitAdapter = HitAdapter()
        val categoryAdapter:CategoryAdapter = CategoryAdapter()
        val productAdapter:ProdtuctAdapter = ProdtuctAdapter()
        binding.hitRcView.layoutManager =
            LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        binding.hitRcView.adapter = adapter
        binding.product.layoutManager =
            GridLayoutManager(binding.root.context,2)
        binding.product.adapter = productAdapter
        binding.category.layoutManager =
            LinearLayoutManager(binding.root.context,LinearLayoutManager.HORIZONTAL,false)
        binding.category.adapter = categoryAdapter

        val viewModel = ViewModelProvider(this)[ProductsViewModel::class.java]

        val cViewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
        viewModel.list().observe(viewLifecycleOwner) {
            adapter.submitList(it)
            productAdapter.submitList(it)
        }
       cViewModel.listCategory().observe(viewLifecycleOwner){
            categoryAdapter.submitList(it)
        }



        categoryAdapter.onClickItem = {
            when(it.id){
                1 ->viewModel.getProductsByCategory(1).observe(viewLifecycleOwner){
                    productAdapter.submitList(it)
                }
                2 ->viewModel.getProductsByCategory(2).observe(viewLifecycleOwner){
                    productAdapter.submitList(it)
                }
            }
        }

        productAdapter.onClick = {
            val action = StoreFragmentDirections.actionStoreFragmentToAboutFragment(it)
            findNavController().navigate(action)
            Log.v(tag,"asd")
        }

    }
}
