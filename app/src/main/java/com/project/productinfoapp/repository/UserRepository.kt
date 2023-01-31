package com.project.productinfoapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.productinfoapp.data.api.UserAPI
import com.project.productinfoapp.data.model.User.UserRequest
import com.project.productinfoapp.data.model.User.UserResponse
import com.project.productinfoapp.utils.NetworkResult

import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val userAPI: UserAPI) {

    private val _userResponseLiveData = MutableLiveData<NetworkResult<UserResponse>>()
    val userResponseLiveData: LiveData<NetworkResult<UserResponse>>
        get() = _userResponseLiveData


    suspend fun loginUser(userRequest: UserRequest) {
        print("resreq....${userRequest.username}")
        print("respass....${userRequest.password}")
        _userResponseLiveData.postValue(NetworkResult.Loading())
        val response =userAPI.signin(userRequest)

        handleResponse(response)
    }

    private fun handleResponse(response: Response<UserResponse>) {
        if (response.isSuccessful && response.body() != null) {
            print("res....${response.body().toString()}")

            _userResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
        }
        else if(response.errorBody()!=null){
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _userResponseLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        }
        else{
            _userResponseLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }
}