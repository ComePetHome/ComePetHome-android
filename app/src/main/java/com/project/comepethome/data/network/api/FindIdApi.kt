package com.project.comepethome.data.network.api

import com.project.comepethome.data.model.FindIdRequest
import com.project.comepethome.data.model.FindIdResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface FindIdApi {

    @POST("api/user/query/findUserId")
    fun findUserId(@Body request: FindIdRequest): Call<FindIdResponse>
}