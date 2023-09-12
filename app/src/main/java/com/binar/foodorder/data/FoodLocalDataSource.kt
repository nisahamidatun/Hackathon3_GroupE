package com.binar.foodorder.data

/**
 * Created by Rahmat Hidayat on 27/08/2023.
 */
class FoodLocalDataSource:FoodDataSource {
    override fun getFoodList(): List<Food> {
        return listOf(
            Food("Ayam Pop","https://raw.githubusercontent.com/Rahmathidayat4299/AssetGambar/master/ayam%20pop.png",40000.0," ayam pop Varian dari pangsit tradisional China yang disajikan dengan cara dikukus dan digoreng."),
            Food("dendengbalado","https://raw.githubusercontent.com/Rahmathidayat4299/AssetGambar/master/dendengbalado.png",50000.0,"Dendeng Varian dari pangsit tradisional China yang disajikan dengan cara dikukus dan digoreng."),
            Food("ikanasampadeh","https://raw.githubusercontent.com/Rahmathidayat4299/AssetGambar/master/ikan%20asam%20padeh.png",60000.0," asam padeh Varian dari pangsit tradisional China yang disajikan dengan cara dikukus dan digoreng."),
            Food("itiakladomudo","https://raw.githubusercontent.com/Rahmathidayat4299/AssetGambar/master/itiakladomudo.png",80000.0,"Varian dari pangsit tradisional China yang disajikan dengan cara dikukus dan digoreng."),
            Food("Ayam Pop","https://raw.githubusercontent.com/Rahmathidayat4299/AssetGambar/master/ayam%20pop.png",40000.0,"Varian dari pangsit tradisional China yang disajikan dengan cara dikukus dan digoreng."),
            Food("dendengbalado","https://raw.githubusercontent.com/Rahmathidayat4299/AssetGambar/master/dendengbalado.png",50000.0,"Varian dari pangsit tradisional China yang disajikan dengan cara dikukus dan digoreng."),
        )
    }
}