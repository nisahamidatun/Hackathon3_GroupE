package com.binar.foodorder.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.binar.foodorder.databinding.ItemFoodFullWidthBinding
import com.binar.foodorder.model.Food

/**
 * Created by Rahmat Hidayat on 10/09/2023.
 */
class ListViewHolder(private val fullWidthBinding: ItemFoodFullWidthBinding,private val onItemClick: (Food) -> Unit) :
    RecyclerView.ViewHolder(fullWidthBinding.root) {
    private val name = fullWidthBinding.tvFoodname
    private val price = fullWidthBinding.tvFoodprice
    private val image = fullWidthBinding.tvFoodimage

    fun bindFullWidth(food: Food) {
        val formattedPrice = "Rp ${food.Price.toInt()}"
        name.text = food.name
        price.text = formattedPrice
        image.load(food.Image)
        fullWidthBinding.root.setOnClickListener { onItemClick.invoke(food) }
    }

}