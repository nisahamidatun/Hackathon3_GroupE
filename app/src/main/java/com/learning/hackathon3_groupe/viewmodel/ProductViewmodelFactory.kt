package com.learning.hackathon3_groupe.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.learning.hackathon3_groupe.repository.ProductRepository

/**
 * Created by Rahmat Hidayat on 20/09/2023.
 */
class ProductViewmodelFactory(private val productRepository: ProductRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductViewmodel::class.java)) {
            return ProductViewmodel(productRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

