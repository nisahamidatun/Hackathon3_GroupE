package com.binar.foodorder.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.binar.foodorder.model.Food
import com.binar.foodorder.databinding.ItemFoodBinding
import com.binar.foodorder.databinding.ItemFoodFullWidthBinding

/**
 * Created by Rahmat Hidayat on 27/08/2023.
 */
class FoodAdapter(private val onItemClick: (Food) -> Unit, private var isGridview: Boolean) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_TYPE_DEFAULT = 0
    private val VIEW_TYPE_FULL_WIDTH = 1

    private val differ =  AsyncListDiffer(this,object : DiffUtil.ItemCallback<Food>(){
        override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    })
    fun setData(data : List<Food>,isGridview: Boolean){
       differ.submitList(data)
        this.isGridview = isGridview
        notifyItemRangeChanged(0,data.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_DEFAULT -> {
                val itemViewBinding = ItemFoodBinding.inflate(inflater, parent, false)
                GridViewHolder(itemViewBinding, onItemClick = onItemClick)
            }

            VIEW_TYPE_FULL_WIDTH -> {
                val itemViewBinding = ItemFoodFullWidthBinding.inflate(inflater, parent, false)
                ListViewHolder(itemViewBinding,onItemClick = onItemClick)
            }

            else -> throw IllegalArgumentException("Invalid viewType: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is GridViewHolder -> {
                holder.bindDefault(differ.currentList[position])
            }

            is ListViewHolder -> {
                holder.bindFullWidth(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
    override fun getItemViewType(position: Int): Int {
        return if (isGridview) VIEW_TYPE_DEFAULT else VIEW_TYPE_FULL_WIDTH
    }

}