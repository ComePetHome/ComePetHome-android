package com.project.comepethome.data.network.repository

import android.util.Log
import com.project.comepethome.data.model.LoginResponse
import com.project.comepethome.data.model.ModifyRequest
import com.project.comepethome.data.model.UploadProfileResponse
import com.project.comepethome.data.network.api.GatewayServiceApi
import com.project.comepethome.ui.main.MainActivity
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyPageModifyRepository {

    private val gatewayServiceApi: GatewayServiceApi
    private val logInRepository = LogInRepository()

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://54.180.142.14:9001/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        gatewayServiceApi = retrofit.create(GatewayServiceApi::class.java)
    }

    fun modifyUserProfile(
        accessToken: String,
        nickName: String,
        name: String,
        phoneNumber: String
    ) {

        val modifyRequest = ModifyRequest(nickName, name, phoneNumber)

        val call = gatewayServiceApi.modifyUserProfile(accessToken, modifyRequest)
        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {

                if (response.isSuccessful) {
                    Log.d("MyPageModifyRepository", "response : ${response}")
                } else {
                    Log.d("MyPageModifyRepository", "Error: ${response}")
                    val errorBody = response.errorBody()?.string()

                    if (errorBody?.contains("\"code\":172") == true) {
                        refreshAccessToken(modifyRequest)
                    }
                }

            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("MyPageModifyRepository", "modifyUserProfile 의 네트워크 오류: ${t.message}")
            }

        })
    }

    fun refreshAccessToken(modifyRequest: ModifyRequest) {
        val call = gatewayServiceApi.modifyUserProfile("${MainActivity.refreshToken}", modifyRequest)
        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {

                if (response.isSuccessful) {
                    Log.d("MyPageModifyRepository", "refreshAccessToken 의 response : ${response}")
                } else {
                    Log.d("MyPageModifyRepository", "refreshAccessToken 의 Error: ${response}")
                    logInRepository.loginUser(
                        MainActivity.loginId,
                        MainActivity.loginPassword,
                        { accessToken, refreshToken ->

                            MainActivity.accessToken = accessToken
                            MainActivity.refreshToken = refreshToken

                            MainActivity.accessToken?.let { newAccessToken ->
                                modifyUserProfile(
                                    newAccessToken,
                                    modifyRequest.nickName,
                                    modifyRequest.name,
                                    modifyRequest.phoneNumber
                                )
                            }
                        },
                        { errorMessage ->

                        }
                    )
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("MyPageModifyRepository", "refreshAccessToken 의 네트워크 오류: ${t.message}")
            }

        })
    }

    fun uploadProfileImg(
        accessToken: String,
        imagePart: MultipartBody.Part,
    ) {

        val call = gatewayServiceApi.uploadProfileImg(accessToken, imagePart)
        call.enqueue(object : Callback<UploadProfileResponse> {
            override fun onResponse(call: Call<UploadProfileResponse>, response: Response<UploadProfileResponse>) {
                if (response.isSuccessful) {
                    val imageUrl = response.body()?.imageUrls?.get(0)


                } else {
                    val errorBody = response.errorBody()?.string()

                    if (errorBody?.contains("\"code\":172") == true) {
                        refreshUploadProfileImg(imagePart)
                    }

                }
            }

            override fun onFailure(call: Call<UploadProfileResponse>, t: Throwable) {

            }

        })

    }

    fun refreshUploadProfileImg(
        imagePart: MultipartBody.Part,
    ) {

        val call = gatewayServiceApi.uploadProfileImg("${MainActivity.refreshToken}", imagePart)
        call.enqueue(object : Callback<UploadProfileResponse> {
            override fun onResponse(call: Call<UploadProfileResponse>, response: Response<UploadProfileResponse>) {

                if (response.isSuccessful) {
                    val imageUrl = response.body()?.imageUrls?.get(0)

                } else {
                    val errorBody = response.errorBody()?.string()

                    logInRepository.loginUser(
                        MainActivity.loginId,
                        MainActivity.loginPassword,
                        { accessToken, refreshToken ->

                            MainActivity.accessToken = accessToken
                            MainActivity.refreshToken = refreshToken

                            MainActivity.accessToken?.let {newAccessToken ->
                                uploadProfileImg(
                                    newAccessToken,
                                    imagePart,
                                )
                            }
                        },
                        { errorMessage ->

                        }
                    )
                }
            }

            override fun onFailure(call: Call<UploadProfileResponse>, t: Throwable) {

            }

        })

    }

    fun updateProfileImg(
        accessToken: String,
        imagePart: MultipartBody.Part
    ){
        val call = gatewayServiceApi.updateProfileImg(accessToken, imagePart)
        call.enqueue(object : Callback<UploadProfileResponse> {
            override fun onResponse(call: Call<UploadProfileResponse>, response: Response<UploadProfileResponse>) {

                if (response.isSuccessful) {

                } else {
                    val errorBody = response.errorBody()?.string()

                    if (errorBody?.contains("\"code\":172") == true) {
                        refreshUpdateProfileImg(imagePart)
                    }

                }
            }

            override fun onFailure(call: Call<UploadProfileResponse>, t: Throwable) {
            }

        })
    }

    fun refreshUpdateProfileImg(imagePart: MultipartBody.Part) {
        val call = gatewayServiceApi.updateProfileImg("${MainActivity.refreshToken}", imagePart)
        call.enqueue(object : Callback<UploadProfileResponse>{
            override fun onResponse(call: Call<UploadProfileResponse>, response: Response<UploadProfileResponse>) {

                if (response.isSuccessful) {

                } else {
                    val errorBody = response.errorBody()?.string()

                    logInRepository.loginUser(
                        MainActivity.loginId,
                        MainActivity.loginPassword,
                        { accessToken, refreshToken ->

                            MainActivity.accessToken = accessToken
                            MainActivity.refreshToken = refreshToken

                            MainActivity.accessToken?.let {newAccessToken ->
                                updateProfileImg(
                                    newAccessToken,
                                    imagePart
                                )
                            }
                        },
                        { errorMessage ->

                        }
                    )
                }
            }

            override fun onFailure(call: Call<UploadProfileResponse>, t: Throwable) {

            }

        })
    }

}