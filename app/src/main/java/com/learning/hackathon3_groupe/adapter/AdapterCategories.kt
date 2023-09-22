package com.learning.hackathon3_groupe.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.learning.hackathon3_groupe.databinding.ItemHomeCategoryBinding
import com.learning.hackathon3_groupe.model.Categories

class CategoryListAdapter(private val onItemClick: (Categories) -> Unit) :
    RecyclerView.Adapter<CategoryListAdapter.CategoryItemViewHolder>() {
    private val differ = AsyncListDiffer(this, object : DiffUtil.ItemCallback<Categories>() {
        override fun areItemsTheSame(oldItem: Categories, newItem: Categories): Boolean {
            return oldItem.name == newItem.name
        }
        override fun areContentsTheSame(oldItem: Categories, newItem: Categories): Boolean {
            return oldItem == newItem
        }
    })

    fun submitData(data: List<Categories>) {
        differ.submitList(data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemViewHolder {
        val binding =
            ItemHomeCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryItemViewHolder, position: Int) {
        val category = differ.currentList[position]
        holder.bind(category)
        holder.itemView.setOnClickListener { onItemClick(category) }
    }

    override fun getItemCount(): Int = differ.currentList.size

    class CategoryItemViewHolder(private val binding: ItemHomeCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Categories) {
            binding.tvCategoryName.text = category.name
            binding.ivCategory.load(category.imageCategories)
        }
    }
}
