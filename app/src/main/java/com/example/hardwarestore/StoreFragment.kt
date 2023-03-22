package com.example.hardwarestore

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.webkit.RenderProcessGoneDetail
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hardwarestore.databinding.FragmentStoreBinding
import com.example.hardwarestore.model.Products
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
        val adapter: HitAdapter = HitAdapter()
        val categoryAdapter:CategoryAdapter = CategoryAdapter()
        val productAdapter:ProdtuctAdapter = ProdtuctAdapter()

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
/*
        binding.textHit.setOnClickListener {
            viewModel.insertProduct("Бензопила", "Хороший бкнзопила качественный не дорогой хай хай бля ", 2500,"Хороший бкнзопила качественный не дорогой хай хай бля больше нечего сказать хочешь бери не хочеш иди нахуй",2,R.drawable.b)
            viewModel.insertProduct("Костюм", "Хороший костюм качественный не дорогой хай хай бля ", 1500,"Хороший бкнзопила качественный не дорогой хай хай бля больше нечего сказать хочешь бери не хочеш иди нахуй",3,R.drawable.kostyum)
            viewModel.insertProduct("Бензопила", "Хороший бкнзопила качественный не дорогой хай хай бля ", 2500,"Хороший бкнзопила качественный не дорогой хай хай бля больше нечего сказать хочешь бери не хочеш иди нахуй",2,R.drawable.b)
            viewModel.insertProduct("Костюм", "Хороший костюм качественный не дорогой хай хай бля ", 1500,"Хороший бкнзопила качественный не дорогой хай хай бля больше нечего сказать хочешь бери не хочеш иди нахуй",3,R.drawable.kostyum)
            viewModel.insertProduct("Бензопила", "Хороший бкнзопила качественный не дорогой хай хай бля ", 2500,"Хороший бкнзопила качественный не дорогой хай хай бля больше нечего сказать хочешь бери не хочеш иди нахуй",2,R.drawable.b)
            viewModel.insertProduct("Костюм", "Хороший костюм качественный не дорогой хай хай бля ", 1500,"Хороший бкнзопила качественный не дорогой хай хай бля больше нечего сказать хочешь бери не хочеш иди нахуй",3,R.drawable.kostyum)
            viewModel.insertProduct("Бензопила", "Хороший бкнзопила качественный не дорогой хай хай бля ", 2500,"Хороший бкнзопила качественный не дорогой хай хай бля больше нечего сказать хочешь бери не хочеш иди нахуй",2,R.drawable.b)
            viewModel.insertProduct("Костюм", "Хороший костюм качественный не дорогой хай хай бля ", 1500,"Хороший бкнзопила качественный не дорогой хай хай бля больше нечего сказать хочешь бери не хочеш иди нахуй",3,R.drawable.kostyum)
            viewModel.insertProduct("Бензопила", "Хороший бкнзопила качественный не дорогой хай хай бля ", 2500,"Хороший бкнзопила качественный не дорогой хай хай бля больше нечего сказать хочешь бери не хочеш иди нахуй",2,R.drawable.b)
            viewModel.insertProduct("Костюм", "Хороший костюм качественный не дорогой хай хай бля ", 1500,"Хороший бкнзопила качественный не дорогой хай хай бля больше нечего сказать хочешь бери не хочеш иди нахуй",3,R.drawable.kostyum)

        }
            binding.textKatalog.setOnClickListener {
                cViewModel.insertCategory("Все")
                cViewModel.insertCategory("Бензопила")
                cViewModel.insertCategory("Костюм")
                cViewModel.insertCategory("Газонокомилки")
                cViewModel.insertCategory("хз")
            }
*/     //доб


        categoryAdapter.onClickItem = {
            when(it.id){
                1 ->viewModel.list().observe(viewLifecycleOwner){
                    productAdapter.submitList(it)
                }
                2 ->viewModel.getProductsByCategory(2).observe(viewLifecycleOwner){
                    productAdapter.submitList(it)
                }
                3 ->viewModel.getProductsByCategory(3).observe(viewLifecycleOwner){
                    productAdapter.submitList(it)
                }
            }
        }

        productAdapter.onClick = {
            val action = StoreFragmentDirections.actionStoreFragmentToAboutFragment(it)
            findNavController().navigate(action)
        }

        binding.searchRv.setHasFixedSize(true)
        binding.searchRv.layoutManager = LinearLayoutManager(requireContext())
        binding.searchRv.adapter = productAdapter

        viewModel.searchLiveData.observe(viewLifecycleOwner) {
            productAdapter.submitList(it)
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

