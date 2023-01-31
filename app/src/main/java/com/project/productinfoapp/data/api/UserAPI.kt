package com.project.productinfoapp.data.api

import com.project.productinfoapp.data.model.User.UserRequest
import com.project.productinfoapp.data.model.User.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAPI {
    @POST("auth/login")
    suspend fun signin(@Body login: UserRequest): Response<UserResponse>

}