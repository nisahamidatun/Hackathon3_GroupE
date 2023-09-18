package com.binar.foodorder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.binar.foodorder.databinding.ItemFoodGridviewBinding
import com.binar.foodorder.databinding.ItemFoodListviewBinding
import com.binar.foodorder.model.Food

/**
 * Created by Rahmat Hidayat on 27/08/2023.
 */
class FoodAdapter(private val onItemClick: (Food) -> Unit, private var isLinearview: Boolean) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_TYPE_GRID = 0
    private val VIEW_TYPE_LIST = 1

    private val differ = AsyncListDiffer(this, object : DiffUtil.ItemCallback<Food>() {
        override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.Image == newItem.Image &&
                    oldItem.Price == newItem.Price
        }

        override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    })

    fun setData(data: List<Food>, isLinearview: Boolean) {
        differ.submitList(data)
        this.isLinearview = isLinearview
    }

    fun refreshList() {
        notifyItemRangeChanged(0, differ.currentList.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_LIST -> {
                val itemViewBinding = ItemFoodListviewBinding.inflate(inflater, parent, false)
                ListViewHolder(itemViewBinding, onItemClick)
            }

            VIEW_TYPE_GRID -> {
                val itemViewBinding = ItemFoodGridviewBinding.inflate(inflater, parent, false)
                GridViewHolder(itemViewBinding, onItemClick)
            }

            else -> throw IllegalArgumentException("Invalid viewType: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ListViewHolder -> {
                holder.bindFullWidth(differ.currentList[position])
            }

            is GridViewHolder -> {
                holder.bindDefault(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
    override fun getItemViewType(position: Int): Int {
        return if (isLinearview) VIEW_TYPE_LIST else VIEW_TYPE_GRID
    }

}