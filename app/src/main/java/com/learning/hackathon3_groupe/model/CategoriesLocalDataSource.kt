package com.learning.hackathon3_groupe.model

/**
 * Created by Rahmat Hidayat on 20/09/2023.
 */
class CategoriesLocalDataSource : CategoriesDataSource {
    override fun getCategories(): List<Categories> {
        return listOf(
            Categories(
                1,
                "Groceries",
                "https://raw.githubusercontent.com/hermasyp/CH3-asset-code-challenge/master/categories/ic_category_groceries.png"
            ),
            Categories(
                2,
                "Fruits",
                "https://raw.githubusercontent.com/hermasyp/CH3-asset-code-challenge/master/categories/ic_category_fruits.png"
            ),
            Categories(
                3,
                "Spices",
                "https://raw.githubusercontent.com/hermasyp/CH3-asset-code-challenge/master/categories/ic_category_spices.png"
            ),
            Categories(
                4,
                "Herbs",
                "https://raw.githubusercontent.com/hermasyp/CH3-asset-code-challenge/master/categories/ic_category_herbs.png"
            ),
            Categories(
                5,
                "Seasoning",
                "https://raw.githubusercontent.com/hermasyp/CH3-asset-code-challenge/master/categories/ic_category_seasoning.png"
            ),
            Categories(
                6,
                "Meat",
                "https://raw.githubusercontent.com/hermasyp/CH3-asset-code-challenge/master/categories/ic_category_meat.png"
            ),
        )
    }
}