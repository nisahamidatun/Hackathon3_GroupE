package com.learning.hackathon3_groupe.repository

import com.learning.hackathon3_groupe.model.Product
import com.learning.hackathon3_groupe.model.ProductDataSource

/**
 * Created by Rahmat Hidayat on 20/09/2023.
 */
class ProductRepository(private val productDataSource: ProductDataSource) {
    fun getProduct():List<Product>{
        return productDataSource.getProduct()
    }
}
