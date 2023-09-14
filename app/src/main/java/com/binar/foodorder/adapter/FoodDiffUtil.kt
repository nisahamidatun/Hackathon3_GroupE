package com.binar.foodorder.adapter

import androidx.recyclerview.widget.DiffUtil
import com.binar.foodorder.model.Food

/**
 * Created by Rahmat Hidayat on 31/08/2023.
 */
class FoodDiffUtil(private val oldList: List<Food>, private val newList: List<Food>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].name == newList[newItemPosition].name
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
