package com.project.productinfoapp.ui.product

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project.productinfoapp.data.model.ProductList.ProductsListItem

import com.project.productinfoapp.databinding.ProductItemBinding

class ProductAdapter(private val listener: ProductsListItemItemListener) : RecyclerView.Adapter<ProductsListItemViewHolder>() {

    interface ProductsListItemItemListener {
        fun onClickedProductsListItem(ProductsListItemId: String)
    }

    private val items = ArrayList<ProductsListItem>()

    fun setItems(items: ArrayList<ProductsListItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsListItemViewHolder {
        val binding: ProductItemBinding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductsListItemViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ProductsListItemViewHolder, position: Int) = holder.bind(items[position])
}

class ProductsListItemViewHolder(private val itemBinding: ProductItemBinding, private val listener: ProductAdapter.ProductsListItemItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var ProductsListItem: ProductsListItem

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    fun bind(item: ProductsListItem) {
        this.ProductsListItem = item
        itemBinding.prodname.text = item.name
        itemBinding.productid.text=item.id.toString()
        itemBinding.price.text=item.price.toString()

    }

    override fun onClick(v: View?) {
        listener.onClickedProductsListItem(ProductsListItem.id)
    }
}