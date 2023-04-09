package com.example.hardwarestore.view

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hardwarestore.model.App
import com.example.hardwarestore.R
import com.example.hardwarestore.databinding.FragmentPaymentBinding
import com.example.hardwarestore.viewmodel.ActivityViewModel
import com.example.hardwarestore.viewmodel.BasketViewModel
import com.example.hardwarestore.viewmodel.HistoryViewModel
import java.util.*


class PaymentFragment : Fragment() {
    private var _binding: FragmentPaymentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPaymentBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val historyViewModel = ViewModelProvider(this)[HistoryViewModel::class.java]
        val activityViewModel = ViewModelProvider(requireActivity())[ActivityViewModel::class.java]
        val basketViewModel = ViewModelProvider(this)[BasketViewModel::class.java]
        var typeCard = ""
        var dataCard = ""
        var i = 0;
        var b = 0;


        binding.editNameCard.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
               binding.textNameCard.text = s
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })


        binding.editNumberCard.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            @SuppressLint("SetTextI18n")
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.toString().isNotEmpty()) {
                   binding.textNumberCard.text = s
                    val input = binding.textNumberCard.text
                    if ( input != null && input.matches(Regex("^\\d{16}$"))){
                        val formattedCardNumber = input.chunked(4).joinToString(" ")
                        binding.textNumberCard.text = formattedCardNumber
                    }

                    if( binding.textNumberCard.text.toString().length <= 1){
                        binding.textNumberCard.text = "1111 1111 1111 1111"
                    }
                }
                if (s.toString().length>=4){
            typeCard = s.toString()
            when(typeCard.substring(0,4)){
               "4444" ->binding.paymentCard.setImageResource(R.drawable.visacard)
               "5058" ->binding.paymentCard.setImageResource(R.drawable.card)
            }
            }else{
                binding.paymentCard.setImageResource(R.drawable.card)
                }
            }
            override fun afterTextChanged(s: Editable?) {

            }

        })

        val dateFormatter = object : TextWatcher {
            private var current = ""
            private val mmddyy = "MM/YY"
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.textDateCard.text = s
            }
            override fun afterTextChanged(s: Editable?) {
                if (s.toString() != current) {
                    var clean = s.toString().replace("[^\\d.]".toRegex(), "")
                    val cleanC = current.replace("[^\\d.]".toRegex(), "")
                    val cl = clean.length
                    var sel = cl
                    var i = 2
                    while (i <= cl && i < 5) {
                        sel++
                        i += 1
                    }
                    if (clean == cleanC) sel--
                    clean = if (clean.length < 4) {
                        "$clean".substring(0, Math.min(4, "$clean".length))
                    } else {
                        val month = clean.substring(0,2)
                        val year = clean.substring(2,4)
                        "$month/$year"
                    }
                    clean = "$clean".substring(0, Math.min(5, "$clean".length))
                    sel = Math.max(sel, 0)
                    current = clean
                    binding.editDateCard.setText(current)
                    binding.editDateCard.setSelection(Math.min(sel, current.length))
                }
            }
        }
       binding.editDateCard.addTextChangedListener(dateFormatter)


        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.pay.setOnClickListener {
            showNotification()

                basketViewModel.getAll(activityViewModel.user!!.id).observe(viewLifecycleOwner) {
                    if (it != null) {
                        for (basket in it)
                            historyViewModel.insertHistory(basket.productId!!, basket.userId!!)
                    }

                }
        }


    }


    private fun showNotification(){
        val notificationManager = activity?.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        val notificationId= 1234

        val notification = NotificationCompat.Builder(requireContext(), App.PAYMENTS_CHANNEL_ID)
            .setContentTitle("Строителный магазин")
            .setContentText("")
            .setColor(ContextCompat.getColor(requireContext(),R.color.gardware_001))
            .setSmallIcon(R.drawable.logo)
            .setStyle(NotificationCompat.BigTextStyle())
            .setAutoCancel(true)
            .build()

        notificationManager.notify(notificationId,notification)
    }
}