package com.project.productinfoapp.data.model.Products


import com.google.gson.annotations.SerializedName

data class ProductsHomeItem(
    @SerializedName("name")
    val name: String,
    @SerializedName("value")
    val value: Int
)