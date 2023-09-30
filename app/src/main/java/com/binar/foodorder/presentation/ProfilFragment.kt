package com.binar.foodorder.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import coil.transform.RoundedCornersTransformation
import com.binar.foodorder.R
import com.binar.foodorder.databinding.FragmentProfilBinding


class ProfilFragment : Fragment() {
    private lateinit var binding: FragmentProfilBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       showItemProfile()

    }

    private fun showItemProfile() {
        binding.ivProfil.load("https://avatars.githubusercontent.com/u/87407102?v=4") {
            transformations(RoundedCornersTransformation(25f))
        }

        binding.editTextName.setText(R.string.name)
        binding.editTextEmail.setText(R.string.email)
        binding.editTextPhone.setText(R.string.no_phone)
    }


}

