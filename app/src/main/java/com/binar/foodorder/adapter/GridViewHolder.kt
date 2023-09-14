package com.binar.foodorder.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.binar.foodorder.model.Food
import com.binar.foodorder.databinding.ItemFoodBinding
import com.bumptech.glide.Glide

/**
 * Created by Rahmat Hidayat on 10/09/2023.
 */
class GridViewHolder(private val binding: ItemFoodBinding):RecyclerView.ViewHolder(binding.root) {
    private val name = binding.tvFoodname
    private val price = binding.tvFoodprice
    private val image = binding.tvFoodimage
    fun bindDefault(food: Food) {
        val formattedPrice = "Rp ${food.Price.toInt()}"
        name.text = food.name
        price.text = formattedPrice
        image.load(food.Image)
    }


}