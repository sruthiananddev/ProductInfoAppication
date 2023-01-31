package com.project.productinfoapp.ui.login

import androidx.lifecycle.ViewModel


import android.text.TextUtils
import androidx.lifecycle.LiveData

import androidx.lifecycle.viewModelScope
import com.project.productinfoapp.data.model.User.UserRequest
import com.project.productinfoapp.data.model.User.UserResponse
import com.project.productinfoapp.repository.UserRepository
import com.project.productinfoapp.utils.Helper
import com.project.productinfoapp.utils.NetworkResult
import com.project.productinfoapp.utils.TokenManager

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val userRepository: UserRepository,private  val tokenManager: TokenManager) : ViewModel() {

    val userResponseLiveData: LiveData<NetworkResult<UserResponse>>
        get() = userRepository.userResponseLiveData


    fun loginUser(userRequest: UserRequest){
        viewModelScope.launch {
            userRepository.loginUser(userRequest)
        }
    }


    fun validateCredentials(emailAddress: String,  password: String,
                            isLogin: Boolean) : Pair<Boolean, String> {

        var result = Pair(true, "")
        if(TextUtils.isEmpty(emailAddress) || (!isLogin && TextUtils.isEmpty(emailAddress)) || TextUtils.isEmpty(password)){
            result = Pair(false, "Please provide the credentials")
        }

        else if(!TextUtils.isEmpty(password) && password.length <= 5){
            result = Pair(false, "Password length should be greater than 5")
        }
        return result
    }

}