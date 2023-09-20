package com.learning.hackathon3_groupe.model

/**
 * Created by Rahmat Hidayat on 20/09/2023.
 */
interface ProductDataSource {
    fun getProduct():List<Product>
}