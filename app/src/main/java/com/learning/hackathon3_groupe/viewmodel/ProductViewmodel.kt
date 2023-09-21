package com.learning.hackathon3_groupe.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.learning.hackathon3_groupe.model.Product
import com.learning.hackathon3_groupe.repository.ProductRepository

/**
 * Created by Rahmat Hidayat on 20/09/2023.
 */
class ProductViewmodel(private val productRepository: ProductRepository):ViewModel() {
    private val _product  = MutableLiveData<List<Product>>()
    val product : LiveData<List<Product>> get() = _product
    init {
        _product.value = productRepository.getProduct()
    }
}
