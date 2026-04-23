package com.examen.appsolissol.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.examen.appsolissol.api.Product
import com.examen.appsolissol.databinding.ItemProductBinding

class ProductAdapter(private var list: List<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = list[position]
        holder.binding.tvTitle.text = product.title
        holder.binding.tvCategory.text = product.category
        holder.binding.tvPrice.text = "Precio: $${product.price}"

        Glide.with(holder.itemView.context)
            .load(product.thumbnail)
            .into(holder.binding.ivThumbnail)
    }

    fun updateData(newList: List<Product>) {
        list = newList
        notifyDataSetChanged()
    }
}
