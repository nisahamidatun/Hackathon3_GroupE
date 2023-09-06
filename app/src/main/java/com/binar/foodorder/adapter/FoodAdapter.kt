package com.binar.foodorder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.binar.foodorder.data.Food
import com.binar.foodorder.databinding.ItemFoodBinding
import com.bumptech.glide.Glide

/**
 * Created by Rahmat Hidayat on 27/08/2023.
 */
class FoodAdapter(private var foods: List<Food>) : RecyclerView.Adapter<FoodAdapter.ViewHolder>() {
    fun updateData(newFoods: List<Food>) {
        val diffResult = DiffUtil.calculateDiff(FoodDiffUtil(foods, newFoods))
        foods = newFoods
        diffResult.dispatchUpdatesTo(this)
    }

    interface OnItemClickListener {
        fun onItemClick(food: Food)
    }

    private var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    class ViewHolder(binding: ItemFoodBinding) : RecyclerView.ViewHolder(binding.root) {
        private var name = binding.tvFoodname
        private var price = binding.tvFoodprice
        private var image = binding.tvFoodimage
        fun bind(food: Food) {
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
        val viewHolder = ViewHolder(itemViewBinding)

        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val clickedFood = foods[position]
                clickedFood.let { itemClickListener?.onItemClick(it) }
            }
        }
        return viewHolder
    }

    override fun getItemCount(): Int = foods.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(foods[position])
    }

}