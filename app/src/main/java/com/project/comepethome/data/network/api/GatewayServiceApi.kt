package com.project.comepethome.data.network.api

import com.project.comepethome.data.model.LoginRequest
import com.project.comepethome.data.model.LoginResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface GatewayServiceApi {
    @Headers("Content-Type: application/json")
    @POST("api/user/command/login")
    fun loginUser(@Body request: LoginRequest): Call<LoginResponse>
}
