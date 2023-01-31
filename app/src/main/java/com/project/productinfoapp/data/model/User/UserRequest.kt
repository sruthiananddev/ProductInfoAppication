package com.project.productinfoapp.data.model.User


import com.google.gson.annotations.SerializedName

data class UserRequest(
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String,

)