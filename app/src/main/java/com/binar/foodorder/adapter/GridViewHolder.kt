package com.binar.foodorder.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.binar.foodorder.databinding.ItemFoodGridviewBinding
import com.binar.foodorder.model.Food

/**
 * Created by Rahmat Hidayat on 10/09/2023.
 */
class GridViewHolder(
    private val binding: ItemFoodGridviewBinding,
    private val onItemClick: (Food) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bindDefault(food: Food) {
        val formattedPrice = "Rp ${food.Price.toInt()}"
        binding.tvFoodname.text = food.name
        binding.tvFoodprice.text = formattedPrice
        binding.tvFoodimage.load(food.Image)
        binding.root.setOnClickListener {
            onItemClick.invoke(food)
        }
    }


}