package com.binar.foodorder.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.binar.foodorder.data.Food
import com.binar.foodorder.repository.FoodRepository

/**
 * Created by Rahmat Hidayat on 27/08/2023.
 */
class FoodViewModel(private val foodRepository: FoodRepository):ViewModel() {
    private val _food  = MutableLiveData<List<Food>>()
    val foods :LiveData<List<Food>> get() = _food
    init {
        _food.value = foodRepository.getFood()
    }
}
