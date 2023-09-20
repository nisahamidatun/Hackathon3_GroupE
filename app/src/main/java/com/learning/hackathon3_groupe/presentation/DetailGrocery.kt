package com.learning.hackathon3_groupe.presentation

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.learning.hackathon3_groupe.R
import com.learning.hackathon3_groupe.databinding.FragmentDetailGroceryBinding
import com.learning.hackathon3_groupe.model.Product


class DetailGrocery : Fragment() {


    private lateinit var binding: FragmentDetailGroceryBinding

    private val grocery : Product? by lazy {
        GroceryDetailFragmentArgs.fromBundle(arguments as Bundle).grocery
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailGroceryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showGroceryData()
    }


    private fun showGroceryData() {
        grocery?.let { p ->
            binding.apply {
                ivFruit.load(p.productImageUrl) {
                    crossfade(true)
                }
                tvTitleDetail.text = p.name
                tvSeller.text = p.supplierName
                tvDescFruit.text = p.desc
            }
        }
    }
}