package com.binar.foodorder.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.binar.foodorder.databinding.ActivityDetailFoodBinding
import com.binar.foodorder.model.Food

class DetailFoodActivity : AppCompatActivity() {
    private  val binding: ActivityDetailFoodBinding by lazy {
        ActivityDetailFoodBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//         Mengambil objek Food dari Intent
        val food = intent.getParcelableExtra<Food>(EXTRA_PRODUCT)

        // Memeriksa apakah objek Food tidak null
        if (food != null) {
            val priceFood = "Rp. ${food.Price.toInt()}"
            binding.imageView.load(food.Image)
            binding.tvFoodDetail.text = food.name
            binding.textPriceDetail.text = priceFood
            binding.tvDescription.text = food.description
        } else {
            // Handle jika objek Food null (opsional)
            // Misalnya, tampilkan pesan bahwa data tidak tersedia.
        }

    }

    companion object {
        const val EXTRA_PRODUCT = "EXTRA_PRODUCT"
        fun startActivity(context: Context, food: Food) {
            val intent = Intent(context, DetailFoodActivity::class.java)
            intent.putExtra(EXTRA_PRODUCT, food)
            context.startActivity(intent)
        }
    }
}