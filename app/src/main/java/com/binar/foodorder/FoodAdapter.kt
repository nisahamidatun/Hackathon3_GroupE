package com.binar.foodorder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.binar.foodorder.data.Food
import com.binar.foodorder.databinding.ItemFoodBinding
import com.bumptech.glide.Glide

/**
 * Created by Rahmat Hidayat on 27/08/2023.
 */
class FoodAdapter(private val foods: List<Food>):RecyclerView.Adapter<FoodAdapter.ViewHolder>(){
    class ViewHolder(binding:ItemFoodBinding):RecyclerView.ViewHolder(binding.root) {
        private var name = binding.foodName
        private var price = binding.foodPrice
        private var image = binding.foodImage
       fun bind(food: Food){
           val formattedPrice = "Rp ${food.Price.toInt()}"
           name.text = food.name
            price.text = formattedPrice
           Glide.with(itemView.context)
               .load(food.Image)
               .into(image)
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemViewBinding =
            ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemViewBinding)
    }

    override fun getItemCount(): Int = foods.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(foods[position])
    }

}