package com.binar.foodorder

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.binar.foodorder.adapter.FoodAdapter

import com.binar.foodorder.data.Food
import com.binar.foodorder.data.FoodLocalDataSource
import com.binar.foodorder.databinding.ActivityMainBinding
import com.binar.foodorder.repository.FoodRepository
import com.binar.foodorder.viewmodel.FoodViewModel
import com.binar.foodorder.viewmodel.FoodViewModelFactory
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    private lateinit var binding :ActivityMainBinding
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

        recyclerView.layoutManager = GridLayoutManager(this,2)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : FoodAdapter.OnItemClickListener {
            override fun onItemClick(food: Food) {
                val itemName = food.name
                showCustomToast(binding.root,"Yourchoice => $itemName")
            }
        })
        foodViewModel.foods.observe(this) { foods ->
            adapter.updateData(foods)
        }
    }

    fun showCustomToast(view: View, message: String) {
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        val snackbarView = snackbar.view
        val params = snackbarView.layoutParams as ViewGroup.MarginLayoutParams
        params.setMargins(16, 0, 16, 16) // Set custom margins if needed
        snackbarView.layoutParams = params
        snackbarView.background = ContextCompat.getDrawable(view.context, R.drawable.bg_custom_toast)

        val textView = snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.setTextColor(ContextCompat.getColor(view.context, android.R.color.white))
        textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        textView.gravity = Gravity.CENTER

        snackbar.show()
    }

}