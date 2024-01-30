package com.project.comepethome.data.network.api

import com.project.comepethome.data.model.LoginRequest
import com.project.comepethome.data.model.LoginResponse
import com.project.comepethome.data.model.ModifyRequest
import com.project.comepethome.data.model.UploadProfileResponse
import com.project.comepethome.data.model.UserProfileResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part

interface GatewayServiceApi {
    @Headers("Content-Type: application/json")
    @POST("api/user/command/login")
    fun loginUser(@Body request: LoginRequest): Call<LoginResponse>

    @GET("api/user/query/profile")
    fun getUserProfile(@Header("access-token") accessToken: String): Call<UserProfileResponse>

    @PATCH("api/user/command/profile")
    fun modifyUserProfile(
        @Header("access-token") accessToken: String,
        @Body request: ModifyRequest
    ): Call<LoginResponse>

    @GET("image/my-profile")
    fun getUserProfileImg(@Header("access-token") accessToken: String): Call<List<String>>

    @Multipart
    @POST("image/my-profile")
    fun uploadProfileImg(
        @Header("access-token") accessToken: String,
        @Part image: MultipartBody.Part
    ): Call<UploadProfileResponse>

    @Multipart
    @PUT("image/my-profile")
    fun updateProfileImg(
        @Header("access-token") accessToken: String,
        @Part image: MultipartBody.Part
    ): Call<UploadProfileResponse>

    @DELETE("image/my-profile")
    fun deleteProfileImg(@Header("access-token") accessToken: String): Call<LoginResponse>

}
