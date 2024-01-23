package com.project.comepethome.data.network.repository

import android.util.Log
import com.project.comepethome.data.model.UserProfileResponse
import com.project.comepethome.data.network.api.GatewayServiceApi
import com.project.comepethome.ui.main.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyPageRepository {

    private val gatewayServiceApi: GatewayServiceApi
    private val logInRepository = LogInRepository()

    val TAG = "MyPageFragment"

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://54.180.142.14:9001/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        gatewayServiceApi = retrofit.create(GatewayServiceApi::class.java)
    }

    fun getUserProfile(accessToken: String,
                       onSuccess: (UserProfileResponse) -> Unit,
                       onFailure: (String) -> Unit
    ) {
        val call = gatewayServiceApi.getUserProfile(accessToken)
        call.enqueue(object : Callback<UserProfileResponse> {
            override fun onResponse(call: Call<UserProfileResponse>, response: Response<UserProfileResponse>) {

                if (response.isSuccessful) {
                    val userProfile = response.body()

                    if (userProfile != null) {
                        onSuccess.invoke(userProfile)
                    }
                }else {
                    onFailure.invoke("Error: ${response.code()}")
                    val errorBody = response.errorBody()?.string()

                    if (errorBody?.contains("\"code\":172") == true) {
                        refreshAccessToken(onSuccess, onFailure)
                    }
                }
            }

            override fun onFailure(call: Call<UserProfileResponse>, t: Throwable) {
                onFailure.invoke("네트워크 오류: ${t.message}")
            }

        })

    }

    fun refreshAccessToken(
        onSuccess: (UserProfileResponse) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val call = gatewayServiceApi.getUserProfile("${MainActivity.refreshToken}")
        call.enqueue(object : Callback<UserProfileResponse> {
            override fun onResponse(call: Call<UserProfileResponse>, response: Response<UserProfileResponse>) {

                if (response.isSuccessful) {
                    val userProfile = response.body()

                    if (userProfile != null) {
                        onSuccess.invoke(userProfile)
                    }
                }else {
                    onFailure.invoke("Error: ${response.code()}")

                    logInRepository.loginUser(
                        MainActivity.loginId,
                        MainActivity.loginPassword,
                        { accessToken, refreshToken ->

                            MainActivity.accessToken = accessToken
                            MainActivity.refreshToken = refreshToken

                            MainActivity.accessToken?.let { getUserProfile(it, onSuccess, onFailure) }
                        },
                        { errorMessage ->

                        }
                    )
                }
            }

            override fun onFailure(call: Call<UserProfileResponse>, t: Throwable) {
                onFailure.invoke("네트워크 오류: ${t.message}")
            }

        })
    }

}