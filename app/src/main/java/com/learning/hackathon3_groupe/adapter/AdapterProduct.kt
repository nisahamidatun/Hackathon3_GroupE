package com.learning.hackathon3_groupe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.learning.hackathon3_groupe.databinding.ItemProductBinding
import com.learning.hackathon3_groupe.model.Product

/**
 * Created by Rahmat Hidayat on 20/09/2023.
 */
class AdapterProduct(private val onItemclick: (Product) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val differ = AsyncListDiffer(this, object : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.no == newItem.no &&
                    oldItem.price == newItem.price &&
                    oldItem.weightInKg == newItem.weightInKg &&
                    oldItem.supplierName == newItem.supplierName &&
                    oldItem.rating == newItem.rating &&
                    oldItem.desc == newItem.desc &&
                    oldItem.productImageUrl == newItem.productImageUrl
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    })
 fun submitData(data:List<Product>){
     differ.submitList(data)
 }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolderProduct(onItemclick, binding)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolderProduct -> {
                val product = differ.currentList[position]
                holder.bind(product)
            }
        }
    }

}
class ViewHolderProduct(
    private val onItemclick: (Product) -> Unit,
    private val itemProductBinding: ItemProductBinding
) : RecyclerView.ViewHolder(itemProductBinding.root) {
    fun bind(product: Product) {
        val formatPrice = "Rp ${product.price.toInt()}"
        itemProductBinding.tvProductName.text = product.name
        itemProductBinding.tvProductrate.text = product.rating.toString()
        itemProductBinding.tvFoodprice.text = formatPrice
        itemProductBinding.ivFoodimage.load(product.productImageUrl)
        itemProductBinding.root.setOnClickListener {
            onItemclick.invoke(product)
        }
    }
}
