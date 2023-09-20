package com.learning.hackathon3_groupe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.learning.hackathon3_groupe.model.Categories
import com.learning.hackathon3_groupe.repository.CategoryRepository

/**
 * Created by Rahmat Hidayat on 20/09/2023.
 */
class CategoriesViewModel(private val categoryRepository: CategoryRepository):ViewModel() {
    private val _categories  = MutableLiveData<List<Categories>>()
    val categories : LiveData<List<Categories>> get() = _categories
    init {
        _categories.value = categoryRepository.getCategory()
    }

}
