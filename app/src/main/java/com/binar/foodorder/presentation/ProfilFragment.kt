
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
        binding.ivProfil.load("https://avatars.githubusercontent.com/u/87407102?v=4") {
            transformations(RoundedCornersTransformation(25f))
        }

        val initialName = R.string.name
        val initialEmail = R.string.email
        val initialPhone = R.string.no_phone

        binding.editTextName.setText(initialName)
        binding.editTextEmail.setText(initialEmail)
        binding.editTextPhone.setText(initialPhone)

    }
}

        return inflater.inflate(R.layout.fragment_profil, container, false)
    }


}

