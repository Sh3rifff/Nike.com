package com.sharif.nikecom.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sharif.nikecom.databinding.RecyclerProductBinding
import com.sharif.nikecom.model.ProductModel
import com.squareup.picasso.Picasso

class PopularAdapter(private val productList: ArrayList<ProductModel>) :
    RecyclerView.Adapter<PopularAdapter.PopularHolder>() {
    class PopularHolder(val binding: RecyclerProductBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularHolder {
        val binding =
            RecyclerProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PopularHolder(binding)
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: PopularHolder, position: Int) {
        holder.binding.productName.text = productList[position].productName
        holder.binding.productPrice.text = productList[position].productPrice
        Picasso.get().load(productList[position].productImage)
            .into(holder.binding.productImage)
    }
}