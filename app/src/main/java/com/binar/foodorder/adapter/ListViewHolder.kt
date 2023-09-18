package com.binar.foodorder.adapter

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.binar.foodorder.databinding.ItemFoodListviewBinding
import com.binar.foodorder.model.Food

/**
 * Created by Rahmat Hidayat on 10/09/2023.
 */
class ListViewHolder(
    private val itemListView: ItemFoodListviewBinding,
    private val onItemClick: (Food) -> Unit
) :
    RecyclerView.ViewHolder(itemListView.root) {

    fun bindFullWidth(food: Food) {
        val formattedPrice = "Rp ${food.Price.toInt()}"
        itemListView.tvFoodname.text = food.name
        itemListView.tvFoodprice.text = formattedPrice
        itemListView.tvFoodimage.load(food.Image)
        itemListView.root.setOnClickListener { onItemClick.invoke(food) }
    }

}