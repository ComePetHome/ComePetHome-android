package com.project.comepethome.data.network.api

import com.project.comepethome.data.model.EmailRequest
import com.project.comepethome.data.model.JoinRequest
import com.project.comepethome.data.model.JoinResponse
import com.project.comepethome.data.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface JoinApi {
    @POST("api/user/command/join")
    fun joinUser(@Body request: JoinRequest): Call<JoinResponse>

    @GET("api/user/query/availableUserId")
    fun checkUserId(@Query("userId") userId: String): Call<String>

    @GET("api/email/verification/request")
    fun sendEmail(@Query("userId") userId: String): Call<LoginResponse>

    @POST("api/email/verification/match-code")
    fun certificationEmail(@Body request: EmailRequest): Call<LoginResponse>

    @POST("api/email/verification/temp-token")
    fun verificationEmail(@Body request: EmailRequest): Call<LoginResponse>

    @POST("api/user/command/change-pw")
    fun changeUserPassword(
        @Header("access-token") accessToken: String,
        @Body password: String
    ): Call<LoginResponse>

}