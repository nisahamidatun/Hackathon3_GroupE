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
import com.binar.foodorder.CounterDataStoreManager
import com.binar.foodorder.MainViewModel
import com.binar.foodorder.R
import com.binar.foodorder.ViewModelFactory
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
    private lateinit var counterDataStoreManager: CounterDataStoreManager
    private var isGridView = false
    private val adapter: FoodAdapter by lazy {
        FoodAdapter(
            onItemClick = { food ->
                navigateToDetailFood(food)
            },
            isGridView
        )
    }
    private lateinit var viewModel: MainViewModel
    private lateinit var pref: CounterDataStoreManager


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
        setUpListToggle()
        isGridView = true
        counterDataStoreManager = CounterDataStoreManager(requireContext())

        pref = CounterDataStoreManager(requireContext())
        viewModel = ViewModelProvider(this, ViewModelFactory(pref))[MainViewModel::class.java]

        // Observasi perubahan isLinearView
        viewModel.getIsLinearView().observe(viewLifecycleOwner) { isGridViews ->
            isGridView = isGridViews
            toggleRecyclerViewLayout()
        }
//        viewLifecycleOwner.lifecycleScope.launch {
//            counterDataStoreManager.getIsLinearView().collect { isLinearView: Boolean ->
//                isLinearview = isLinearView
//                toggleRecyclerViewLayout()
//            }
//        }
    }

    private fun setUpRecycleview() {
        val foodDataSource = FoodLocalDataSource()
        val foodRepository = FoodRepository(foodDataSource)
        val viewModelFactory = FoodViewModelFactory(foodRepository)
        foodViewModel = ViewModelProvider(this, viewModelFactory)[FoodViewModel::class.java]
        val recyclerView = binding.recycleviewFood
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        val linearLayoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
        foodViewModel.foods.observe(viewLifecycleOwner) { foods ->
            adapter.setData(foods, false)
        }
    }

    private fun navigateToDetailFood(food: Food? = null) {
        val action = HomeFoodDirections.actionHomeFoodToDetailFood(food)
        findNavController().navigate(action)
    }

    private fun setUpListToggle() {
        val iconList = binding.iconList
//        pref = CounterDataStoreManager(requireContext())
//        viewModel = ViewModelProvider(this, ViewModelFactory(pref))[MainViewModel::class.java]
        iconList.setOnClickListener {
            isGridView = !isGridView
            toggleRecyclerViewLayout()
            // Untuk menyimpan status isLinearView

            viewModel.setIsLinearView(isGridView)
        }
    }

    private fun toggleRecyclerViewLayout() {
        val recyclerView = binding.recycleviewFood
        val iconList = binding.iconList

        if (isGridView) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            iconList.setImageResource(R.drawable.baseline_list_24)
        } else {
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
            iconList.setImageResource(R.drawable.icon_grid)
        }

        adapter.setData(foodViewModel.foods.value ?: emptyList(), isGridView)
        adapter.refreshList()
    }

}