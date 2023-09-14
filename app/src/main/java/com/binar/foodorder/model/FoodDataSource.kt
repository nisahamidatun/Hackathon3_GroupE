package com.binar.foodorder.model

/**
 * Created by Rahmat Hidayat on 27/08/2023.
 */
interface FoodDataSource {
      fun getFoodList():List<Food>
}