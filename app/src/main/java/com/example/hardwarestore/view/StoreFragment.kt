package com.example.hardwarestore.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hardwarestore.R
import com.example.hardwarestore.databinding.FragmentStoreBinding
import com.example.hardwarestore.viewmodel.CategoryViewModel
import com.example.hardwarestore.viewmodel.ProductsViewModel
import com.example.hardwarestore.viewmodel.RegistrationViewModel
import java.util.*

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
        val searchAdapter:SearchAdapter = SearchAdapter()
        val adapter: HitAdapter = HitAdapter()
        val categoryAdapter: CategoryAdapter = CategoryAdapter()
        val productAdapter: ProdtuctAdapter = ProdtuctAdapter()



        binding.search.setOnQueryTextFocusChangeListener { _, hasFocus ->
            if(hasFocus) {
                binding.layout.visibility = GONE
                binding.searchRv.visibility = VISIBLE
            } else {
                binding.layout.visibility = VISIBLE
                binding.searchRv.visibility = GONE
            }
        }


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
        val userViewModel = ViewModelProvider(this)[RegistrationViewModel::class.java]
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
                1 ->viewModel.list().observe(viewLifecycleOwner){
                    productAdapter.submitList(it)
                }
                2 ->viewModel.getProductsByCategory(2).observe(viewLifecycleOwner){
                    productAdapter.submitList(it)
                }
                3 ->viewModel.getProductsByCategory(6).observe(viewLifecycleOwner){
                    productAdapter.submitList(it)
                }
                4 ->viewModel.getProductsByCategory(7).observe(viewLifecycleOwner){
                    productAdapter.submitList(it)
                }
                5 ->viewModel.getProductsByCategory(3).observe(viewLifecycleOwner){
                    productAdapter.submitList(it)
                }
                6 ->viewModel.getProductsByCategory(4).observe(viewLifecycleOwner){
                    productAdapter.submitList(it)
                }
                7 ->viewModel.getProductsByCategory(5).observe(viewLifecycleOwner){
                    productAdapter.submitList(it)
                }

            }
        }

        productAdapter.onClick = {
            val action =
                StoreFragmentDirections.actionStoreFragmentToAboutFragment(
                    it
                )
            findNavController().navigate(action)
        }

        binding.searchRv.setHasFixedSize(true)
        binding.searchRv.layoutManager = GridLayoutManager(requireContext(),2)
        binding.searchRv.adapter = searchAdapter

        viewModel.searchLiveData.observe(viewLifecycleOwner) {
            searchAdapter.submitList(it)
        }
        binding.search.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText != null)
                    viewModel.search(newText)

                return true
            }
        })


    }

}

