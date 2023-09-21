package com.learning.hackathon3_groupe.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.learning.hackathon3_groupe.adapter.AdapterProduct
import com.learning.hackathon3_groupe.databinding.FragmentHomeGroceryBinding
import com.learning.hackathon3_groupe.model.CategoriesLocalDataSource
import com.learning.hackathon3_groupe.model.Product
import com.learning.hackathon3_groupe.model.ProductLocalDataSource
import com.learning.hackathon3_groupe.repository.CategoryRepository
import com.learning.hackathon3_groupe.repository.ProductRepository
import com.learning.hackathon3_groupe.viewmodel.CategoriesViewModel
import com.learning.hackathon3_groupe.viewmodel.CategoriesViewModelFactory
import com.learning.hackathon3_groupe.viewmodel.ProductViewmodel
import com.learning.hackathon3_groupe.viewmodel.ProductViewmodelFactory

/**
 * A simple [Fragment] subclass.
 * Use the [HomeGrocery.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeGrocery : Fragment() {
    private lateinit var binding: FragmentHomeGroceryBinding
    private lateinit var productViewModel: ProductViewmodel
    private lateinit var categoriesViewModel: CategoriesViewModel

    private val adapterProduct: AdapterProduct by lazy {
        AdapterProduct { product ->
            navigateToDetailProduct(product)
        }
    }

    private fun navigateToDetailProduct(product: Product) {
        findNavController().navigate(HomeGroceryDirections.actionHomeGroceryToDetailGrocery(product))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeGroceryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycleviewProduct()
    }

    private fun setUpRecycleviewProduct() {
        val productDataSource = ProductLocalDataSource()
        val productRepository = ProductRepository(productDataSource)
        val viewmodelFactory = ProductViewmodelFactory(productRepository)
        productViewModel =
            ViewModelProvider(this, viewmodelFactory).get(ProductViewmodel::class.java)
        val recyleviewProduct = binding.listProductRecycleview
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        recyleviewProduct.layoutManager = gridLayoutManager
        recyleviewProduct.adapter = adapterProduct
        productViewModel.product.observe(viewLifecycleOwner) { product ->
            adapterProduct.submitData(product)

        }
    }

    private fun setUpRecycleviewCategories() {
        val categoriestDataSource = CategoriesLocalDataSource()
        val categoriesRepository = CategoryRepository(categoriestDataSource)
        val viewModelFactory = CategoriesViewModelFactory(categoriesRepository)
        categoriesViewModel =
            ViewModelProvider(this, viewModelFactory).get(categoriesViewModel::class.java)
    }
}