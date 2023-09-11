package com.binar.foodorder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.binar.foodorder.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    //    private lateinit var foodViewModel: FoodViewModel
//    private var isGridview = true
//    private val gridLayoutManager = GridLayoutManager(this,2)
//    private val linearLayoutManager = LinearLayoutManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        val homeFragment = HomeFood()
        transaction.add(R.id.fragmentContainer,homeFragment)
        transaction.commit()
//        val foodDataSource = FoodLocalDataSource()
//        val foodRepository = FoodRepository(foodDataSource)
//        val viewModelFactory = FoodViewModelFactory(foodRepository)
//        foodViewModel = ViewModelProvider(this, viewModelFactory)[FoodViewModel::class.java]
//        val recyclerView = binding.recycleviewFood
//        val adapter = FoodAdapter(foodViewModel.foods.value ?: emptyList(),isGridview)
//        recyclerView.layoutManager = gridLayoutManager
//        recyclerView.adapter = adapter
//        val iconList = binding.iconList
//        iconList.setOnClickListener {
//            isGridview = !isGridview
//            if (isGridview){
//                recyclerView.layoutManager = gridLayoutManager
//                iconList.setImageResource(R.drawable.icon_grid)
//
//            }else{
//                recyclerView.layoutManager = linearLayoutManager
//                iconList.setImageResource(R.drawable.baseline_list_24)
//            }
//            adapter.updateData(foodViewModel.foods.value ?: emptyList(), isGridview)
//
//        }
//        adapter.setOnItemClickListener(object :FoodAdapter.OnItemClickListener{
//            override fun onItemClick(food: Food) {
//                val itemName = food.name
//                FancyToast.makeText(
//                    this@MainActivity,
//                    "Your choices food $itemName !",
//                    FancyToast.LENGTH_LONG,
//                    FancyToast.INFO,
//                    true
//                ).show()
//            }
//
//        })
//        foodViewModel.foods.observe(this) { foods ->
//            adapter.updateData(foods,true)
//        }
    }
}