package com.binar.foodorder.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.binar.foodorder.R
import com.binar.foodorder.adapter.FoodAdapter
import com.binar.foodorder.databinding.FragmentHomeFoodBinding
import com.binar.foodorder.model.Food
import com.binar.foodorder.model.FoodLocalDataSource
import com.binar.foodorder.repository.FoodRepository
import com.binar.foodorder.viewmodel.FoodViewModel
import com.binar.foodorder.viewmodel.FoodViewModelFactory


class HomeFood : Fragment() {

    private lateinit var binding: FragmentHomeFoodBinding
    private lateinit var foodViewModel: FoodViewModel

    private var isGridview = true
    private val adapter: FoodAdapter by lazy {
        FoodAdapter(
            onItemClick = { food ->
                navigateToDetailFood(food)
            },
            isGridview = true
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeFoodBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycleview()
    }
    private fun setUpRecycleview(){
        val foodDataSource = FoodLocalDataSource()
        val foodRepository = FoodRepository(foodDataSource)
        val viewModelFactory = FoodViewModelFactory(foodRepository)
        foodViewModel = ViewModelProvider(this, viewModelFactory)[FoodViewModel::class.java]
        val recyclerView = binding.recycleviewFood
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        val linearLayoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = adapter
        val iconList = binding.iconList
        iconList.setOnClickListener {
            isGridview = !isGridview
            if (isGridview) {
                recyclerView.layoutManager = gridLayoutManager
                iconList.setImageResource(R.drawable.icon_grid)
            } else {
                recyclerView.layoutManager = linearLayoutManager
                iconList.setImageResource(R.drawable.baseline_list_24)
            }
            adapter.setData(foodViewModel.foods.value ?: emptyList(), isGridview)
        }

        foodViewModel.foods.observe(viewLifecycleOwner) { foods ->
            adapter.setData(foods, true)
        }
    }

    private fun navigateToDetailFood(food: Food? = null) {
        val action = HomeFoodDirections.actionHomeFoodToDetailFood(food)
        findNavController().navigate(action)
    }

}