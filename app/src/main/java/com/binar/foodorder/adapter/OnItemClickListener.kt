package com.binar.foodorder.adapter

import com.binar.foodorder.data.Food

/**
 * Created by Rahmat Hidayat on 10/09/2023.
 */
interface OnItemClickListener {
    fun onItemClick(food: Food)
}

var itemClickListener: OnItemClickListener? = null

fun setOnItemClickListener(listener: OnItemClickListener) {
    itemClickListener = listener
}