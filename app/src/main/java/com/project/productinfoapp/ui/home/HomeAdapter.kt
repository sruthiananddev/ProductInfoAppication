package com.project.productinfoapp.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.productinfoapp.data.model.Products.ProductsHomeItem
import com.project.productinfoapp.databinding.HomeItemBinding

class HomeAdapter(private val listener: ProductsHomeItemItemListener) : RecyclerView.Adapter<ProductsHomeItemViewHolder>() {

    interface ProductsHomeItemItemListener {
        fun onClickedProductsHomeItem(ProductsHomeItemId: String)
    }

    private val items = ArrayList<ProductsHomeItem>()

    fun setItems(items: ArrayList<ProductsHomeItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsHomeItemViewHolder {
        val binding: HomeItemBinding = HomeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductsHomeItemViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ProductsHomeItemViewHolder, position: Int) = holder.bind(items[position])
}

class ProductsHomeItemViewHolder(private val itemBinding: HomeItemBinding, private val listener: HomeAdapter.ProductsHomeItemItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var ProductsHomeItem: ProductsHomeItem

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: ProductsHomeItem) {
        this.ProductsHomeItem = item
        itemBinding.title.text = item.name
        itemBinding.desc.text=item.value.toString()

    }

    override fun onClick(v: View?) {
        listener.onClickedProductsHomeItem(ProductsHomeItem.name)
    }
}