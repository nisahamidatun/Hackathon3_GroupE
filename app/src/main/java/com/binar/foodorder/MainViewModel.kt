package com.binar.foodorder

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

/**
 * Created by Rahmat Hidayat on 25/09/2023.
 */
class MainViewModel(private val pref: CounterDataStoreManager) : ViewModel() {


    val vIsLinearView: LiveData<Boolean> = pref.getIsLinearView().asLiveData() // Menambah LiveData untuk isLinearView

    // Menambahkan fungsi untuk mengatur isLinearView
    fun setIsLinearView(isLinear: Boolean) {
        viewModelScope.launch {
            pref.setIsLinearView(isLinear)
        }
    }

    // Menambahkan fungsi untuk mendapatkan isLinearView
    fun getIsLinearView(): LiveData<Boolean> {
        return vIsLinearView
    }
}
