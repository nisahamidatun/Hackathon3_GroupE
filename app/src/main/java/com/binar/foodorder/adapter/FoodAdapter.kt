package com.binar.foodorder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.binar.foodorder.model.Food
import com.binar.foodorder.databinding.ItemFoodBinding
import com.binar.foodorder.databinding.ItemFoodFullWidthBinding

/**
 * Created by Rahmat Hidayat on 27/08/2023.
 */
class FoodAdapter(private var foods: List<Food>, private var isGridview: Boolean) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_TYPE_DEFAULT = 0
    private val VIEW_TYPE_FULL_WIDTH = 1
    interface OnItemClickListener {
        fun onItemClick(food: Food)
    }

    var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }
    fun updateData(newFoods: List<Food>, isGridview: Boolean) {
        val diffResult = DiffUtil.calculateDiff(FoodDiffUtil(foods, newFoods))
        foods = newFoods
        this.isGridview = isGridview
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_DEFAULT -> {
                val itemViewBinding = ItemFoodBinding.inflate(inflater, parent, false)
                GridViewHolder(itemViewBinding)
            }

            VIEW_TYPE_FULL_WIDTH -> {
                val itemViewBinding = ItemFoodFullWidthBinding.inflate(inflater, parent, false)
                ListViewHolder(itemViewBinding)
            }

            else -> throw IllegalArgumentException("Invalid viewType: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is GridViewHolder -> {
                holder.bindDefault(foods[position])
            }

            is ListViewHolder -> {
                holder.bindFullWidth(foods[position])
            }
        }
        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(foods[position])
        }
    }

    override fun getItemCount(): Int = foods.size
    override fun getItemViewType(position: Int): Int {
        return if (isGridview) VIEW_TYPE_DEFAULT else VIEW_TYPE_FULL_WIDTH
    }

}