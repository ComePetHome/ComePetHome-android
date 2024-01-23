package com.project.comepethome.data.network.api

import com.project.comepethome.data.model.LoginRequest
import com.project.comepethome.data.model.LoginResponse
import com.project.comepethome.data.model.UserProfileResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface GatewayServiceApi {
    @Headers("Content-Type: application/json")
    @POST("api/user/command/login")
    fun loginUser(@Body request: LoginRequest): Call<LoginResponse>

    @GET("api/user/query/profile")
    fun getUserProfile(@Header("access-token") accessToken: String): Call<UserProfileResponse>
}
