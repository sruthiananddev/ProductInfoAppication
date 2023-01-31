package com.project.productinfoapp.data.api

import com.project.productinfoapp.data.model.ProductDetails.ProductDetails
import com.project.productinfoapp.data.model.ProductList.ProductsList
import com.project.productinfoapp.data.model.Products.ProductsHome
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductAPI {
    @GET("/home")
    suspend fun gethome(): Response<ProductsHome>
    @GET("/product")
    suspend fun getproducts(): Response<ProductsList>
    @GET("/product/get/{id}")
    suspend fun getproductdetails(@Path("id") id: String): Response<ProductDetails>
}