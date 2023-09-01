package com.binar.foodorder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.binar.foodorder.adapter.FoodAdapter
import com.binar.foodorder.data.Food
import com.binar.foodorder.data.FoodLocalDataSource
import com.binar.foodorder.databinding.ActivityMainBinding
import com.binar.foodorder.repository.FoodRepository
import com.binar.foodorder.viewmodel.FoodViewModel
import com.binar.foodorder.viewmodel.FoodViewModelFactory
import com.shashank.sony.fancytoastlib.FancyToast


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var foodViewModel: FoodViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val foodDataSource = FoodLocalDataSource()
        val foodRepository = FoodRepository(foodDataSource)
        val viewModelFactory = FoodViewModelFactory(foodRepository)
        foodViewModel = ViewModelProvider(this, viewModelFactory)[FoodViewModel::class.java]
        val recyclerView = binding.recycleviewFood
        val adapter = FoodAdapter(foodViewModel.foods.value ?: emptyList())

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : FoodAdapter.OnItemClickListener {
            override fun onItemClick(food: Food) {
                val itemName = food.name
                FancyToast.makeText(
                    this@MainActivity,
                    "Your choices food $itemName !",
                    FancyToast.LENGTH_LONG,
                    FancyToast.INFO,
                    true
                ).show()
            }
        })
        foodViewModel.foods.observe(this) { foods ->
            adapter.updateData(foods)
        }
    }

}