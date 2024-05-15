package com.example.demo.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.data.Product
import com.example.demo.databinding.ItemProductBinding

class ProductAdapter (
    val fn: (ViewHolder, Product) -> Unit = { _, _ -> }
) : ListAdapter<Product, ProductAdapter.ViewHolder>(Diff) {

    companion object Diff : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(a: Product, b: Product) = a.id == b.id
        override fun areContentsTheSame(a: Product, b: Product) = a == b
    }

    class ViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = getItem(position)

        // NOTE: Extension function --> ImageView.setImageByteArray()
        holder.binding.imgPhoto.setImageByteArray(product.photo)
        holder.binding.txtId.text = product.id
        holder.binding.txtName.text = product.name
        holder.binding.txtPrice.text = "%.2f".format(product.price)

        fn(holder, product)
    }

}