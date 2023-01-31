package com.project.productinfoapp.repository

import androidx.lifecycle.MutableLiveData
import com.project.productinfoapp.data.api.ProductAPI
import com.project.productinfoapp.data.model.ProductDetails.ProductDetails
import com.project.productinfoapp.utils.NetworkResult
import org.json.JSONObject

import javax.inject.Inject

class ProductDetailsRepository @Inject constructor(private val noteAPI: ProductAPI) {

    private val _productsLiveData = MutableLiveData<NetworkResult<ProductDetails>>()
    val productsLiveData get() = _productsLiveData

    private val _statusLiveData = MutableLiveData<NetworkResult<Pair<Boolean, String>>>()
    val statusLiveData get() = _statusLiveData



    suspend fun getproductdetails(id:String) {

        _productsLiveData.postValue(NetworkResult.Loading())
        val response = noteAPI.getproductdetails(id)
        if (response.isSuccessful && response.body() != null) {

            _productsLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _productsLiveData.postValue(NetworkResult.Error(errorObj.getString("errorMessage")))
        } else {
            _productsLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }



}