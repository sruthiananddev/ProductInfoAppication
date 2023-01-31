package com.project.productinfoapp.data.model.ProductList


import com.google.gson.annotations.SerializedName

data class ProductsListItem(
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Double
)