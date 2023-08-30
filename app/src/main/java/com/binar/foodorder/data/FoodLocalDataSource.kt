package com.binar.foodorder.data

/**
 * Created by Rahmat Hidayat on 27/08/2023.
 */
class FoodLocalDataSource:FoodDataSource {
    override fun getFoodList(): List<Food> {
        return listOf(
            Food("Ayam Pop","https://raw.githubusercontent.com/Rahmathidayat4299/AssetGambar/master/ayam%20pop.png",40000.0),
            Food("dendengbalado","https://raw.githubusercontent.com/Rahmathidayat4299/AssetGambar/master/dendengbalado.png",50000.0),
            Food("ikanasampadeh","https://raw.githubusercontent.com/Rahmathidayat4299/AssetGambar/master/ikan%20asam%20padeh.png",60000.0),
            Food("itiakladomudo","https://raw.githubusercontent.com/Rahmathidayat4299/AssetGambar/master/itiakladomudo.png",80000.0),
            Food("Ayam Pop","https://raw.githubusercontent.com/Rahmathidayat4299/AssetGambar/master/ayam%20pop.png",40000.0),
            Food("dendengbalado","https://raw.githubusercontent.com/Rahmathidayat4299/AssetGambar/master/dendengbalado.png",50000.0),
        )
    }
}