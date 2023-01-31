package com.project.productinfoapp.repository

import androidx.lifecycle.MutableLiveData
import com.project.productinfoapp.data.api.ProductAPI
import com.project.productinfoapp.data.model.ProductList.ProductsList
import com.project.productinfoapp.utils.NetworkResult
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class ProductListRepository @Inject constructor(private val noteAPI: ProductAPI) {

    private val _productsLiveData = MutableLiveData<NetworkResult<ProductsList>>()
    val productsLiveData get() = _productsLiveData

    private val _statusLiveData = MutableLiveData<NetworkResult<Pair<Boolean, String>>>()
    val statusLiveData get() = _statusLiveData



    suspend fun getproducts() {
        _productsLiveData.postValue(NetworkResult.Loading())
        val response = noteAPI.getproducts()
        if (response.isSuccessful && response.body() != null) {
            println("res...."+response.body().toString())
            _productsLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _productsLiveData.postValue(NetworkResult.Error(errorObj.getString("errorMessage")))
        } else {
            _productsLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }



    private fun handleResponse(response: Response<ProductsList>, message: String) {
        if (response.isSuccessful && response.body() != null) {
            _statusLiveData.postValue(NetworkResult.Success(Pair(true, message)))
        } else {
            _statusLiveData.postValue(NetworkResult.Success(Pair(false, "Something went wrong")))
        }
    }
}