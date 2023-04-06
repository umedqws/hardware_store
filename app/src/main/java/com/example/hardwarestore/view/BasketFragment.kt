package com.example.hardwarestore.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hardwarestore.R
import com.example.hardwarestore.databinding.FragmentBasketBinding
import com.example.hardwarestore.viewmodel.ActivityViewModel
import com.example.hardwarestore.viewmodel.BasketViewModel
import com.example.hardwarestore.viewmodel.HistoryViewModel
import com.example.hardwarestore.viewmodel.ProductsViewModel
import java.util.*


class BasketFragment : Fragment(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {
    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0

    var saveDay = 0
    var saveMonth = 0
    var saveYear = 0
    var saveHour = 0
    var saveMinute = 0


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

    @SuppressLint("SuspiciousIndentation", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val basketViewModel = ViewModelProvider(this)[BasketViewModel::class.java]

        val typeOrder: MutableList<String> = ArrayList()
        val typeBuy: MutableList<String> = ArrayList()
        val list = listOf("Курером", "Самовызов")
        val listBuy = listOf("Банковская карта", "Наличкой", "Bitcoin", "Etherium")
        for (i in list) {
            typeOrder.add(i)
        }
        for (i in listBuy) {
            typeBuy.add(i)
        }
        val spinnerAdapter: ArrayAdapter<String> = ArrayAdapter(
            requireContext(),
            R.layout.spinner, typeOrder
        )
        val spinnerBuyAdapter: ArrayAdapter<String> = ArrayAdapter(
            requireContext(),
            R.layout.spinner, typeBuy
        )

        spinnerBuyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = spinnerBuyAdapter
        binding.spinner.onItemSelectedListener = object : OnItemSelectedListener {
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
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spiiner.adapter = spinnerAdapter
        binding.spiiner.onItemSelectedListener = object : OnItemSelectedListener {
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
        val historyViewModel = ViewModelProvider(this)[HistoryViewModel::class.java]

        basketAdapter = BasketAdapter()
        binding.RecucleViewBasket.adapter = basketAdapter

        basketAdapter.onClick = { sm ->
            binding.total.text = "${(binding.total.text.split(" ")[0].toInt() + sm)} sm"
            binding.bonusPayment.text = "${(binding.total.text.split(" ")[0].toInt() + sm) / 10} sm"
        }

        basketAdapter.onDelete = {
            basketViewModel.delete(it,activityViewModel.user!!.id)
        }



        basketViewModel.getProductsForBasket(activityViewModel.user!!.id)
            .observe(viewLifecycleOwner) {
                basketAdapter.submitList(it)
                if (activityViewModel.user!!.id == activityViewModel.user!!.id) {
                    binding.total.text = "0"


                    var total = 0
                    for (i in it) {
                        total += i.price!!
                        binding.total.text = "$total sm"
                        binding.bonusPayment.text = "${total / 10}"
                    }
                    for (i in it.indices) {
                        binding.quantity.text = "${i + 1} товара"
                    }
                }



                }

        binding.payment.setOnClickListener {
            findNavController().navigate(R.id.action_basketFragment2_to_paymentFragment)
        }

        pickTime()
    }

    private fun getDateCalendar() {
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)
    }

    private fun pickTime() {
        binding.time.setOnClickListener {
            getDateCalendar()
            TimePickerDialog(requireContext(), this, hour, minute, true).show()

        }
        binding.date.setOnClickListener {
            getDateCalendar()
            DatePickerDialog(requireContext(), this, year, month, day).show()
        }
    }


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        saveDay = dayOfMonth
        saveMonth = month
        saveYear = year

        binding.date.text = "$saveDay.$saveMinute.$saveYear"

    }



    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        saveHour = hourOfDay
        saveMinute = minute

        binding.time.text = "$saveHour : $saveMinute"

    }


}



