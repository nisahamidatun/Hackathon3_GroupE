package com.learning.hackathon3_groupe.repository

import com.learning.hackathon3_groupe.model.Categories
import com.learning.hackathon3_groupe.model.CategoriesLocalDataSource
import com.learning.hackathon3_groupe.model.Product
import com.learning.hackathon3_groupe.model.ProductDataSource

/**
 * Created by Rahmat Hidayat on 20/09/2023.
 */
class CategoryRepository(private val categoriesLocalDataSource: CategoriesLocalDataSource) {
    fun getCategory():List<Categories>{
        return categoriesLocalDataSource.getCategories()
    }
}