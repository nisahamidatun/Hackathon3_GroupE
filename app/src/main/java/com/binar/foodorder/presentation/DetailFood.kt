package com.binar.foodorder.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.binar.foodorder.model.Food
import com.binar.foodorder.databinding.FragmentDetailFoodBinding
import com.bumptech.glide.Glide


class DetailFood : Fragment() {
    private lateinit var binding: FragmentDetailFoodBinding
    private var quantity = 1

    private val  food: Food? by lazy{
        DetailFoodArgs.fromBundle(arguments as Bundle).food
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailFoodBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val image = binding.imageView

        food.let {
            val priceFood = "Rp. ${food?.Price?.toInt()}"
            image.load(food?.Image)
            binding.tvFoodDetail.text = food?.name
            binding.textPriceDetail.text = priceFood
            binding.tvDescription.text = food?.description
        }
        binding.mapView.setOnClickListener {
            val gmmIntentUri = Uri.parse("https://maps.app.goo.gl/h4wQKqaBuXzftGK77")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            startActivity(mapIntent)
        }
        binding.icBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.tvDecrement.setOnClickListener {
            if (quantity > 1) {
                quantity--
                updateCartButton()
            }
        }
        binding.tvIncrement.setOnClickListener {
            quantity++
            updateCartButton()
        }
        updateCartButton()
    }

    private fun updateCartButton() {
        val food = food
        val pricePerItem = food?.Price ?: 0

        val totalPrice = quantity * pricePerItem.toInt()
        val priceFood = "Rp. $totalPrice"

        val resultPrice =
            if (quantity > 1) "Tambahkan  ke Keranjang - $priceFood" else "Tambahkan  ke Keranjang - $priceFood"

        binding.tvQuantity.text = quantity.toString()

        binding.btnCart.text = resultPrice
    }

}