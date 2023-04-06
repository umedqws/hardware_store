package com.example.hardwarestore.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hardwarestore.databinding.FragmentAboutBinding
import com.example.hardwarestore.viewmodel.ActivityViewModel
import com.example.hardwarestore.viewmodel.BasketViewModel
import com.example.hardwarestore.viewmodel.RegistrationViewModel
import com.example.hardwarestore.viewmodel.ReviewViewModel
import kotlin.math.roundToLong

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
        val activityViewModel = ViewModelProvider(requireActivity())[ActivityViewModel::class.java]
        val reviewViewmodel = ViewModelProvider(this)[ReviewViewModel::class.java]
        val args:AboutFragmentArgs by navArgs()
        binding.imageAbout.setImageResource(args.products.image!!)
        binding.name.text = args.products.nameProduct
        binding.price.text = "${args.products.price} sm"
        binding.about.text = args.products.aboutProduct
        binding.backKatalog.setOnClickListener {
            findNavController().popBackStack()
        }
        val adapter = ReviewAdapter()

        var rating:Int = 0
        binding.commentRecycler.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.commentRecycler.adapter = adapter

        binding.sendButton.setOnClickListener {
            reviewViewmodel.insert(binding.ratingBar.rating.toInt(),binding.editTextTextMultiLine.text.toString(),activityViewModel.user!!.firstName.toString(),args.products.id)

        }

        binding.ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            // выполнить действия при изменении значения
        }

        reviewViewmodel.getImage(args.products.id).observe(viewLifecycleOwner) {
            var one = 0
            var two = 0
            var three = 0
            var four = 0
            var five = 0
            adapter.submitList(it)
            for (i in it) {
                when (i.rating) {
                    1 -> one += i.rating
                    2 -> two += i.rating
                    3 -> three += i.rating
                    4 -> four += i.rating
                    5 -> five += i.rating
                }

            }
            binding.oneProgressIndicator.progress = one
            binding.twoProgressIndicator.progress = two
            binding.threeProgressIndicator.progress = three
            binding.fourProgressIndicator.progress = four
            binding.fiveProgressIndicator.progress = five
            var finish = 0
            var count = 1
            for (i in it){
                finish += i.rating
                count++
            }
            val itogo:Float = finish.toFloat()/count.toFloat()
            binding.finishRating.text = "${itogo.toString().substring(0..2)}/5"
        }


        basketViewModel.getProductsForBasket(activityViewModel.user!!.id).observe(viewLifecycleOwner) {listProduct->
            if (listProduct == null){
                binding.inBasket.text = "Добавиьт в корзину"
            }
                    for (i in listProduct) {
                    if (args.products.id == i.id) {
                        binding.inBasket.text = "Уже в корзине"
                    }
                }

        }

            binding.inBasket.setOnClickListener {
                basketViewModel.insert(
                    args.products.id,
                    activityViewModel.user!!.id
                )
            }
        }
    }
