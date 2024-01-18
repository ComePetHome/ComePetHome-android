package com.project.comepethome.data.network.api

import com.project.comepethome.data.model.JoinRequest
import com.project.comepethome.data.model.JoinResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface JoinApi {
    @POST("api/user/command/join")
    fun joinUser(@Body request: JoinRequest): Call<JoinResponse>

    @GET("api/user/query/availableUserId")
    fun checkUserId(@Query("userId") userId: String): Call<String>

}