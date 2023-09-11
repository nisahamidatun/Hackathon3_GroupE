package com.binar.foodorder

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.binar.foodorder.adapter.FoodAdapter
import com.binar.foodorder.data.Food
import com.binar.foodorder.data.FoodLocalDataSource
import com.binar.foodorder.databinding.FragmentHomeFoodBinding
import com.binar.foodorder.repository.FoodRepository
import com.binar.foodorder.viewmodel.FoodViewModel
import com.binar.foodorder.viewmodel.FoodViewModelFactory
import com.shashank.sony.fancytoastlib.FancyToast


class HomeFood : Fragment() {

    private lateinit var binding: FragmentHomeFoodBinding
    private lateinit var foodViewModel: FoodViewModel
    private var isGridview = true
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeFoodBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val foodDataSource = FoodLocalDataSource()
        val foodRepository = FoodRepository(foodDataSource)
        val viewModelFactory = FoodViewModelFactory(foodRepository)
        foodViewModel = ViewModelProvider(this,viewModelFactory)[FoodViewModel::class.java]
        val recyclerView = binding.recycleviewFood
        val adapter = FoodAdapter(emptyList(), isGridview)
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
            adapter.updateData(foodViewModel.foods.value ?: emptyList(), isGridview)
        }

        foodViewModel.foods.observe(viewLifecycleOwner) { foods ->
            adapter.updateData(foods, true)
        }
        adapter.setOnItemClickListener(object :FoodAdapter.OnItemClickListener{
            override fun onItemClick(food: Food) {
                navigateToDetailFragment(food)
                val itemName = food.name
                FancyToast.makeText(
                    requireContext(),
                    "Your choices food $itemName !",
                    FancyToast.LENGTH_LONG,
                    FancyToast.INFO,
                    true
                ).show()
            }

        })

    }

    private fun navigateToDetailFragment(food: Food) {
        val detailFragment = DetailFood.newInstance(food)

        val fragmentManager = parentFragmentManager
        fragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer,detailFragment, DetailFood::class.java.simpleName)
            addToBackStack(null)
            commit()
        }
    }
}