package com.project.productinfoapp.data.model.ProductDetails


import com.google.gson.annotations.SerializedName

data class ProductDetails(
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