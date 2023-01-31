package com.project.productinfoapp.repository

import androidx.lifecycle.MutableLiveData
import com.project.productinfoapp.data.api.ProductAPI
import com.project.productinfoapp.data.model.Products.ProductsHome
import com.project.productinfoapp.utils.NetworkResult

import org.json.JSONObject
import javax.inject.Inject

class HomeRepository @Inject constructor(private val HomeAPI: ProductAPI) {

    private val _notesLiveData = MutableLiveData<NetworkResult<ProductsHome>>()
    val notesLiveData get() = _notesLiveData

    private val _statusLiveData = MutableLiveData<NetworkResult<Pair<Boolean, String>>>()
    val statusLiveData get() = _statusLiveData



    suspend fun getNotes() {
        _notesLiveData.postValue(NetworkResult.Loading())
        val response = HomeAPI.gethome()
        if (response.isSuccessful && response.body() != null) {

            _notesLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _notesLiveData.postValue(NetworkResult.Error(errorObj.getString("errorMessage")))
        } else {
            _notesLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }



}