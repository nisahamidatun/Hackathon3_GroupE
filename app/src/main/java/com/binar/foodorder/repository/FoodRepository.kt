package com.binar.foodorder.repository

import com.binar.foodorder.data.Food
import com.binar.foodorder.data.FoodLocalDataSource

/**
 * Created by Rahmat Hidayat on 27/08/2023.
 */
class FoodRepository(private val foodLocalDataSource: FoodLocalDataSource) {
     fun getFood():List<Food>{
        return foodLocalDataSource.getFoodList()
    }
}