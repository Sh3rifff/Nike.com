package com.sharif.nikecom.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sharif.nikecom.databinding.CategoryRecyclerBinding
import com.sharif.nikecom.model.CategoryModel

class CategoryAdapter(private val categoryList: ArrayList<CategoryModel>) :
    RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {
    class CategoryHolder(val binding: CategoryRecyclerBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        val binding =
            CategoryRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryHolder(binding)
    }

    override fun getItemCount(): Int = categoryList.size

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {

        holder.binding.categoryName.text = categoryList[position].category
        holder.binding.categoryName.setOnClickListener {
            holder.binding.categoryName.setTextColor(Color.WHITE)
        }
//        holder.binding.categoryName.setBackgroundColor(Resources.getSystem().getColor(R.color.main))

    }
}