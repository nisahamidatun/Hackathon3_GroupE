package com.binar.foodorder

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.binar.foodorder.data.Food
import com.binar.foodorder.databinding.FragmentDetailFoodBinding
import com.bumptech.glide.Glide


class DetailFood : Fragment() {
    private var food: Food? = null
    private lateinit var binding: FragmentDetailFoodBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        food = arguments?.getParcelable(ARG_FOOD)

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
            val priceFood = "Rp. ${food?.Price}"
            Glide.with(this)
                .load(food?.Image)
                .into(image)
            binding.tvFoodDetail.text = food?.name
            binding.textPriceDetail.text = priceFood
        }
        binding.mapView.setOnClickListener {
            // Buat Intent untuk membuka Google Maps dengan link yang diberikan
            val gmmIntentUri = Uri.parse("https://maps.app.goo.gl/h4wQKqaBuXzftGK77")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            startActivity(mapIntent)
        }
    }

    companion object {
        private const val ARG_FOOD = "food"
    }

}