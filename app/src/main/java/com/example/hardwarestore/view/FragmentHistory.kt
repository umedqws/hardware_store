package com.example.hardwarestore.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hardwarestore.databinding.FragmentHistoryBinding
import com.example.hardwarestore.viewmodel.ActivityViewModel
import com.example.hardwarestore.viewmodel.HistoryViewModel

class FragmentHistory : Fragment() {
   private var _binding: FragmentHistoryBinding? = null
        private val binding get() = _binding!!
    val adapter = ProdtuctAdapter()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding = FragmentHistoryBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            binding.historyRcView.layoutManager = GridLayoutManager(requireContext(),2)
            binding.historyRcView.adapter = adapter

            val historyViewModel = ViewModelProvider(this)[HistoryViewModel::class.java]
            val activityViewModel = ViewModelProvider(requireActivity())[ActivityViewModel::class.java]
            historyViewModel.getHistoryByProductForHistory(activityViewModel.user!!.id).observe(viewLifecycleOwner){
                adapter.submitList(it)
                for (i in it.indices){
                binding.quantity.text = "${i+1}"
                }
            }

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
    }


}
