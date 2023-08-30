package com.binar.foodorder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.binar.foodorder.data.Food
import com.bumptech.glide.Glide

/**
 * Created by Rahmat Hidayat on 27/08/2023.
 */
class FoodAdapter(private val foods: List<Food>):RecyclerView.Adapter<FoodAdapter.ViewHolder>(){
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
       fun bind(food: Food){
           val formattedPrice = "Rp ${food.Price.toInt()}"
            itemView.findViewById<TextView>(R.id.foodName).text = food.name
            itemView.findViewById<TextView>(R.id.foodPrice).text = formattedPrice
           Glide.with(itemView.context)
               .load(food.Image)
               .into(itemView.findViewById(R.id.foodImage))
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_food, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = foods.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(foods[position])
    }

}